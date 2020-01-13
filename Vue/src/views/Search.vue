<template>
  <v-container fill-height fluid grid-list-xl>
    <v-layout wrap row>
      <v-flex md10 sm12 xs12 lg8 offset-lg2 offset-md1>
        <v-card dense>
          <v-toolbar dense flat color="white">
            <v-btn-toggle v-model="type" mandatory>
              <v-btn flat value="0">搜索问题</v-btn>
              <v-btn flat value="1">搜索回答</v-btn>
            </v-btn-toggle>
          </v-toolbar>
          <v-divider/>
          <v-card
            v-if="data.length===0"
            height="200px"
            flat
            dense
            style="text-align:center;line-height:200px"
          >
            <h2>
              <b>试试别的关键字</b>或
              <b>前往首页提问吧~</b>
            </h2>
          </v-card>
          <v-card
            color="#fff"
            v-for="(item,index) in data"
            :key="index+1"
            @click="quesGoTo(item.questionID)"
            style="cursor: pointer;"
            dense
            flat
          >
            <v-card-title style="background-color:#E3F2FD" v-if="item.title!==''">
              <span class="title font-weight-bold">{{item.title}}</span>
            </v-card-title>
            <v-card-text class="font-weight-light" v-html="mdtoHtml(item.detail)"/>
            <v-toolbar flat dense color="white">
              <div class="font-weight-light">{{item.showtime}}</div>
              <v-spacer></v-spacer>
              <v-icon v-if="type==='0'" color="red">favorite</v-icon>
              <v-icon v-if="type==='1'" color="amber">star</v-icon>&nbsp;
              <span>{{item.star}}</span>&nbsp;
            </v-toolbar>
            <v-divider/>
          </v-card>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import { mavonEditor } from "mavon-editor";
import { type } from "os";
export default {
  data() {
    return {
      type: "0",
      data: [],
      lock: false,
      answers: [],
      questions: []
    };
  },
  computed: {
    keyword() {
      return this.$route.params.keyword;
    }
  },
  created() {
    this.search();
  },
  watch: {
    $route(to, from) {
      if (to.params.keyword !== from.params.keyword) {
        this.data = [];
        this.questions = [];
        this.answers = [];
        this.lock = false;
        this.search();
      }
    },
    type: "initData"
  },
  methods: {
    search() {
      if (!this.lock) {
        this.lock = true;
        this.$axios
          .post("http://127.0.0.1:2333/search", {
            keyword: this.keyword,
            type: "0"
          })
          .then(
            function(response) {
              response.data.forEach(q => {
                const que = {};
                que.title = q._source.question;
                if (typeof q.highlight!="undefined") {
                  que.detail = q.highlight.detail;
                }
                else{
                  que.detail=q._source.detail;
                }
                que.questionID = q._source.questionID;
                que.showtime = q._source.showTime;
                que.username = q._source.userName;
                que.star = q._source.star;
                this.questions.push(que);
              });
              this.$axios
                .post("http://127.0.0.1:2333/search", {
                  keyword: this.keyword,
                  type: "1"
                })
                .then(
                  function(response) {
                    response.data.forEach(q1 => {
                      const que1 = {};
                      que1.title = "";
                      que1.detail = q1.highlight.answer;
                      que1.questionID = q1._source.questionID;
                      que1.showtime = q1._source.showTime;
                      que1.username = q1._source.userName;
                      que1.star = q1._source.star;
                      this.answers.push(que1);
                    });
                    this.initData();
                  }.bind(this)
                );
            }.bind(this)
          );
      }
    },
    initData() {
      this.data = [];
      if (this.type === "0" || this.type === 0) {
        this.data = this.questions;
      } else {
        this.data = this.answers;
      }
    },
    mdtoHtml(detail) {
      var md = mavonEditor.getMarkdownIt();
      return md.render(String(detail));
    },
    quesGoTo(id) {
      this.$router.push(`/question/${id}`);
    }
  }
};
</script>
