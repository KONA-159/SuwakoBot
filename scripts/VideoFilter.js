// ==UserScript==
// @name         视频分类器
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       Shimarin
// @match        https://space.bilibili.com/*/video*
// @icon         https://www.bilibili.com/favicon.ico
// @grant        GM_registerMenuCommand
// @grant        GM_xmlhttpRequest
// @require      https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js
// ==/UserScript==

$(function () {
    'use strict';
    console.log("Filter开始运行")
    const spaceApiUrl = 'https://api.bilibili.com/x/space/wbi/arc/search?mid='
    let uid = window.location.href.match(/[0-9]+/)[0]
    console.log(uid)
    new Promise(async (resolve, reject) => {
        let spaceRequest = request({
            data: "",
            url: spaceApiUrl + uid,
        })
        let filter = {};
        let spaceContent = await spaceRequest
        let typeid = spaceContent.data.list.vlist[0].typeid
        console.log(typeof typeid)
        spaceContent.data.list.vlist.forEach(it => {
            let typeid = it.typeid
            let bvid = it.bvid
            if (filter[typeid] === undefined) {
                filter[typeid] = []
            }
            filter[typeid].push(bvid)
        })
        console.log(filter)
        let picklist = Object.keys(filter)

        let pick;
        // if(pick.value===undefined){
        pick = picklist[Symbol.iterator]()
        let i;
        // } 
        setTimeout(() => {
            let elements = document.querySelectorAll(".small-item,.fakeDanmu-item")
            console.log(elements)
            GM_registerMenuCommand('切换分区', e => {

                
                i = pick.next().value
                if (i === undefined) {
                    console.log("i="+i)
                    pick = picklist[Symbol.iterator]()
                    i=pick.next().value
                }
                console.log("正在切换")
                for (let li of elements) {
                    li.style.display = 'block';
                    let bvid = li.getAttribute("data-aid")
                    console.log(bvid)
                    console.log(i)
                    if (!filter[i].includes(bvid)) {
                        li.style.display = 'none'
                    }
                }
            })
        }, 1000)
        resolve(filter)
    })



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

});


