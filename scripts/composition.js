// ==UserScript==
// @name         兴趣标签检测器
// @version      0.1
// @author       Shimarin
// @license      GPLv3
// @description  B站评论区自动标注成分，支持动态和关注识别，默认包括原神玩家和王者荣耀玩家
// @match        https://www.bilibili.com/video/*
// @match        https://www.bilibili.com/read/*
// @match        https://t.bilibili.com/*
// @icon         https://www.google.com/s2/favicons?sz=64&domain=tampermonkey.net
// @connect      bilibili.com
// @grant        GM_xmlhttpRequest
// @require      https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js
// ==/UserScript==

//todo 懒加载

$(function () {
    //检测器
    const checkers = [
        {
            checkerName: "原神",
            keywords: ["原神", "米哈游", "miHoYo"],
            followings: [401742377]
        },
        {
            checkerName: "游戏王",
            keywords: [],
            followings: [599193,4738771,519200091,6382823,10922568]
        },
        {
            checkerName: "东方",
            keywords: ["车万"],
            followings: [517717593,11007230,12246,448823]
        },
    ]

    const spaceApiUrl = 'https://api.bilibili.com/x/polymer/web-dynamic/v1/feed/space?&host_mid='
    const followingApiUrl = 'https://api.bilibili.com/x/relation/followings?vmid='

    //更换api
    //关注列表根据关键词匹配

    console.log("兴趣标签检测器初始化中......")


    // 监听用户ID元素出现
    waitForKeyElements(".info .user .name", checkComposition);
    waitForKeyElements(".card-user-name", checkComposition);

    console.log("兴趣标签检测器初始化完成")

    function installComposition(element, setting, weight) {
        //todo 联动
        let node = $(`<div style="display: inline;">
  <a class="composition-name">【${setting.checkerName}|${weight==0?"仅关注":weight}】</a></div>`)
        element.after(node)
    }

    function installNullComposition(element) {
        //todo 联动
        let node = $(`<div style="display: inline;">
  <a class="composition-name">【无】</a></div>`)
        element.after(node)
    }

    function checkComposition(element) {

        let userID = element.attr("href").match(/[0-9]*$/)[0]
        let name = element.text()

        new Promise(async (resolve, reject) => {
            // 找到的匹配内容
            let found = []
            let weights = []

            let spaceRequest = request({
                data: "",
                url: spaceApiUrl + userID,
            })

            let followingRequest = request({
                data: "",
                url: followingApiUrl + userID,
            })


            let spaceContent = await spaceRequest

            // 如果有权限则为0
            if (spaceContent.code == 0) {
                // 解析并拼接动态数据
                let st = JSON.stringify(spaceContent.data.items)

                for (let setting of checkers) {
                    // 检查动态内容
                    if (setting.keywords) {
                        let weight = 0
                        setting.keywords.forEach(keyword => { weight+=getStrNumInParentStr(st,keyword) })
                        if (weight != 0) {
                            if (found.indexOf(setting) < 0) {
                                found.push(setting)
                                weights.push(weight)
                            }
                        }
                    }
                }
            }



            let followingContent = await followingRequest

            // 如果有权限则为0
            let following = followingContent.code == 0 ? followingContent.data.list.map(it => it.mid) : []
            if (following) {
                for (let setting of checkers) {
                    // 检查关注列表
                    if (setting.followings)
                        for (let mid of setting.followings) {
                            if (following.indexOf(mid) >= 0) {
                                if (found.indexOf(setting) < 0){
                                    found.push(setting)
                                    weights.push(0)
                                }
                            }
                        }
                }
            }

            console.log(found)
            console.log(weights)

            // 添加标签
            if (found.length > 0) {
                weight=weights[Symbol.iterator]()
                for (let setting of found) {
                    installComposition(element, setting,weight.next().value)
                    console.log("已添加标签")
                }
            }
            else {
                installNullComposition(element)
            }
            resolve(found)

            //todo 收藏、投币
            //黑名单
            //big data
        })
    }

    function request(option) {
        return new Promise((resolve, reject) => {
            let requestFunction = GM_xmlhttpRequest ? GM_xmlhttpRequest : GM.xmlHttpRequest

            requestFunction({
                method: "get",
                headers: {
                    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36'
                },
                ...option,
                onload: (response) => {
                    let res = JSON.parse(response.responseText)
                    resolve(res)
                },
                onerror: (error) => {
                    reject(error);
                }
            });
        })
    }


    /*--- waitForKeyElements():  A utility function, for Greasemonkey scripts,
        that detects and handles AJAXed content.
        Usage example:
            waitForKeyElements (
                "div.comments"
                , commentCallbackFunction
            );
            //--- Page-specific function to do what we want when the node is found.
            function commentCallbackFunction (jNode) {
                jNode.text ("This comment changed by waitForKeyElements().");
            }
        IMPORTANT: This function requires your script to have loaded jQuery.
        */

    /*function waitForKeyElements (
        selectorTxt,     Required: The jQuery selector string that
                            specifies the desired element(s).

        actionFunction,  Required: The code to run when elements are
                            found. It is passed a jNode to the matched
                            element.

        bWaitOnce,       Optional: If false, will continue to scan for
                            new elements even after the first match is
                            found.

        iframeSelector   Optional: If set, identifies the iframe to
                            search.

    )*/
    function waitForKeyElements(selectorTxt, actionFunction, bWaitOnce, iframeSelector) {
        var targetNodes, btargetsFound;
    
        if (typeof iframeSelector == "undefined")
            targetNodes = $(selectorTxt);
        else
            targetNodes = $(iframeSelector).contents()
                .find(selectorTxt);
    
        if (targetNodes && targetNodes.length > 0) {
            btargetsFound = true;
            targetNodes.each(function () {
                var jThis = $(this);
                var alreadyFound = jThis.data('alreadyFound') || false;
    
                if (!alreadyFound) {
                    //--- Call the payload function.
                    var cancelFound = actionFunction(jThis);
                    if (cancelFound) btargetsFound = false;
                    else jThis.data('alreadyFound', true);
                }
            });
        } else {
            btargetsFound = false;
        }
    
        //--- Get the timer-control variable for this selector.
        var controlObj = waitForKeyElements.controlObj || {};
        var controlKey = selectorTxt.replace(/[^\w]/g, "_");
        var timeControl = controlObj[controlKey];
    
        //--- Now set or clear the timer as appropriate.
        if (btargetsFound && bWaitOnce && timeControl) {
            //--- The only condition where we need to clear the timer.
            clearInterval(timeControl);
            delete controlObj[controlKey]
        } else {
            //--- Set a timer, if needed.
            if (!timeControl) {
                timeControl = setInterval(function () {
                    waitForKeyElements(selectorTxt, actionFunction, bWaitOnce, iframeSelector);
                }, 300);
                controlObj[controlKey] = timeControl;
            }
        }
        waitForKeyElements.controlObj = controlObj;
    }
    


var getStrNumInParentStr = function(parentStr, childStr) {
    if (typeof(parentStr) !== "string" || typeof(childStr) !== "string") {
        return 0;
    }

    if (parentStr.length < childStr.length) {
        return 0;
    }

    let nNum = 0;
    for (let i = 0; i < parentStr.length; i++) {
        let bOkFlag = true;
        let k = i;
        for (let n = 0; n < childStr.length; k++, n++) {
            if (parentStr[k] !== childStr[n]) {
                bOkFlag = false;
                break;
            }
        }

        if (bOkFlag) {
            nNum++;
        }
    }

    return nNum;
}

})
