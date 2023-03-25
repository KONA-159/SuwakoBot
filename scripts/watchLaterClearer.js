// ==UserScript==
// @name         自动删除已观看视频（稍后再看）
// @namespace    http://tampermonkey.net/
// @version      0.1
// @author       Shimarin
// @match        https://www.bilibili.com/watchlater/*
// @icon         https://www.google.com/s2/favicons?sz=64&domain=tampermonkey.net
// @grant        none
// ==/UserScript==

(function() {
    'use strict';
    window.onload=function(){
    var btn=document.getElementsByClassName("s-btn");
    if(btn.length>2){
        btn[2].click();
    }
    var confirmBtn=document.getElementsByClassName("bi-btn btn-submit");
    if(confirmBtn.length>0){
        confirmBtn[0].click();
    }
}
    // Your code here...
})();