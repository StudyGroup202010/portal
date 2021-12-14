$(function() {
    // エンターキーを無効にする
    // 無効にしておかないと登録画面で入力中にエンターキーを押下するとsubmitされて「戻る」ボタンが動いてしまうため。
    //　エンターキーのキーコードは13
    // inputのみを無効にします（textareaなどではエンターキーで改行したいため）

    $("input").on("keydown", function(ev) {
        if ((ev.which && ev.which === 13) || (ev.keyCode && ev.keyCode === 13)) {
            return false;
        } else {
            return true;
        }
    });

    // 郵便番号検索
    $('#getAddressBtn').on('click', function() {
        let zip = $('#zipcode').val();
        if (!zip) {
            // 空の時はなにもしない
        } else {
            $.ajax({
                url: "https://zipcloud.ibsnet.co.jp/api/search",
                type: "GET",
                data: { zipcode: zip },
                dataType: "JSONP"
            })
                .done(function(value) {
                    if (value.results == null) {
                        alert("入力された郵便番号に対する住所がありません。");
                        $("#prefcode").val("");
                        $("#address1").val("");
                        $("#address2").val("");
                        $("#address3").val("");
                        $("#kana1").val("");
                        $("#kana2").val("");
                        $("#kana3").val("");
                    } else {
                        let result = value.results[0];
                        $("#prefcode").val(result.prefcode);
                        $("#address1").val(result.address1);
                        $("#address2").val(result.address2);
                        $("#address3").val(result.address3);
                        $("#kana1").val(result.kana1);
                        $("#kana2").val(result.kana2);
                        $("#kana3").val(result.kana3);
                    }
                })
                .fail(function() {
                    alert("郵便番号検索が出来ませんでした。");
                });
        }
    });

    // 都道府県検索
    $('#address1').on('change', function() {
        //　検索後は他項目を初期化します。
        $("#zipcode").val("");
        $("#prefcode").val("");
        $("#address2").val("");
        $("#address3").val("");
        $("#kana1").val("");
        $("#kana2").val("");
        $("#kana3").val("");
    });
    
    // クリアボタンの押下時の処理を設定(共通)
    $("#resetBtn").on("click", function() {
        clearResetFiled();
    });
    // 項目のリセット処理詳細
    // idがreset-area内のclassにreset-field、reset-checkboxが指定してある要素を
    // すべて空にします。
    var clearResetFiled = function () {
        $("#reset-area").each(function() {
            $(this).find(".reset-field").val("");
            $(this).find(".reset-checkbox").prop("checked", false);
        });
    };

});
