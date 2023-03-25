// ==UserScript==
// @name         B站动态过滤器
// @version      0.1
// @description  20230312
// @author       Shimarin
// @match        https://t.bilibili.com/*
// @icon         https://www.bilibili.com/favicon.ico
// @grant        GM_registerMenuCommand
// ==/UserScript==

/* http://sellmoney.cn/tamp/B%E7%AB%99%E5%8A%A8%E6%80%81%E5%8D%9A%E4%B8%BB%E8%BF%87%E6%BB%A4.user.js  */

(() => {
    let nameList = localStorage.nameList; //加载网页时，获取之前保存的博主列表。

    //连接，多种自定义
    GM_registerMenuCommand('设置要显示的博主名字列表', e => {  //增加油猴设置按钮
        let p = prompt('请输入要显示的博主名字列表，多个请用逗号间隔', localStorage.nameList);
        if (!p)
            return;
        p = p.trim().replaceAll('，', ','); //将中文逗号替换成英文逗号
        localStorage.nameList = p //并保存博主列表。
        nameList = ',' + p + ',';
        alert('设置成功');
    });

    if(!nameList) return;
    nameList = ',' + nameList + ',';

    document.body.addEventListener('DOMSubtreeModified', e => {  //监听网页元素变化
        if (window.flag) //节流，避免死循环。
            return;
        window.flag = true;
        setTimeout(() => window.flag = false, 300);  //节流，避免死循环。
        setTimeout(() => {
            for (let div of document.querySelectorAll('div.bili-dyn-item__main:not([marked])')) { //遍历所有未标记过的卡片
                let name = div.querySelector('span.bili-dyn-title__text').innerText.trim();  //获取卡片的作者名
                div.setAttribute('marked', 1); //标为已标记
                if (!nameList.includes(',' + name + ',')) { //判断是否在作者列表里
                    div.style.display='none';  //隐藏元素
                }
            };
        }, 200);
    });

})();
//主页黑名单
