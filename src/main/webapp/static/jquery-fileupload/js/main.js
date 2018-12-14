/*
 * jQuery File Upload Plugin JS Example
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * https://opensource.org/licenses/MIT
 */

/* global $, window */
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}

$(function () {
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
        // Uncomment the following to send cross-domain cookies:取消注释，发送跨域cookie:
        //xhrFields: {withCredentials: true},
        url: Posturl,
        // done: function (e, data) {
        //     if (data.result.length && data.result.length) {//是否有结果
        //         $.each(data.result.files, function (index, file) {
        //             var str = window.opener.document.getElementById(parentId).value;
        //             var substr = file.name + "|";
        //             console.log("file.name:" + substr);
        //             console.log("input:" + str);
        //             console.log(isContains(str, substr));
        //             if (!isContains(str, substr)) {//是否上传的已存在
        //                 window.opener.document.getElementById(parentId).value += file.name + "|";
        //             }
        //             console.log(window.opener.document.getElementById(parentId).value);
        //         })
        //     }
        // }
    });

    // Enable iframe cross-domain access via redirect option:通过重定向选项启用iframe跨域访问
    $('#fileupload').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/cors/result.html?%s'
        )
    );

    if (window.location.hostname === '') {
        // Demo settings:
        $('#fileupload').fileupload('option', {
            url: $('#fileupload').fileupload('option', 'url'),
            // Enable image resizing, except for Android and Opera,
            // which actually support image resizing, but fail to
            // send Blob objects via XHR requests:
            disableImageResize: /Android(?!.*Chrome)|Opera/
                .test(window.navigator.userAgent),
            maxFileSize: 999000,
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
        });
        // Upload server status check for browsers with CORS support:
        if ($.support.cors) {
            $.ajax({
                url: $('#fileupload').fileupload('option', 'url'),
                type: 'HEAD'
            }).fail(function () {
                $('<div class="alert alert-danger"/>')
                    .text('Upload server currently unavailable - ' +
                        new Date())
                    .appendTo('#fileupload');
            });
        }
    } else {
        // Load existing files:
        $('#fileupload').addClass('fileupload-processing');
        $.ajax({
            // Uncomment the following to send cross-domain cookies:
            //xhrFields: {withCredentials: true},
            url: $('#fileupload').fileupload('option', 'url'),
            dataType: 'json',
            context: $('#fileupload')[0]
        }).always(function () {
            $(this).removeClass('fileupload-processing');
        }).done(function (result) {
            // console.log("success:" + result.length);
            // $(this).fileupload('option', 'done')
            //     .call(this, $.Event('done'), {result: result});//上传完成之后
            $('#fileupload').bind('fileuploaddone',function (e,data) {
                // console.log(data.result);
                if (data.result) {//是否有结果
                    $.each(data.result.files, function (index, file) {
                        var str = window.opener.document.getElementById(parentId).value;
                        var substr = file.name + "|";
                        console.log("file.name:" + substr);
                        console.log("file.url:" + file.url);
                        console.log("input:" + str);
                        console.log(isContains(str, substr));
                        if (!isContains(str, substr)) {//是否上传的已存在
                            window.opener.document.getElementById(parentId).value += file.url + "|";
                        }
                        console.log(window.opener.document.getElementById(parentId).value);
                    })
                }
            })
        });
    }

});
// $("document").ready(function () {
//     console.log("document");
//     $('#fileupload')
//         .bind('fileuploadsubmit', function (e, data){
//
//         })
//     $('#fileupload')
//         .bind('fileuploaddone', function (e, data) {
//             // Event handler example. Do something if you need after file has been deleted on the server.
//             // (Refer to the client side documentatio).
//             if (data.result.length) {//是否有结果
//                 $.each(data.result.files, function (index, file) {
//                     var str = window.opener.document.getElementById(parentId).value;
//                     var substr = file.name + "|";
//                     console.log("file.name:" + substr);
//                     console.log("input:" + str);
//                     console.log(isContains(str, substr));
//                     if (!isContains(str, substr)) {//是否上传的已存在
//                         window.opener.document.getElementById(parentId).value += file.name + "|";
//                     }
//                     console.log(window.opener.document.getElementById(parentId).value);
//                 })
//             }
//         })
// });
