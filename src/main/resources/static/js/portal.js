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
                    if (value.message == null) {
                        let result = value.results[0];
                        $("#prefcode").val(result.prefcode);
                        $("#address1").val(result.address1);
                        $("#address2").val(result.address2);
                        $("#address3").val(result.address3);
                        $("#kana1").val(result.kana1);
                        $("#kana2").val(result.kana2);
                        $("#kana3").val(result.kana3);
                    } else {
                        alert("入力された郵便番号に対する住所がありません");
                    }
                })
                .fail(function() {
                    alert("郵便番号検索でエラーが発生しました。");
                });
        }
    });
});