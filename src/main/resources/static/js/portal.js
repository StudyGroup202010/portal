// エンターキーを無効にする
// 無効にしておかないと登録画面で入力中にエンターキーを押下するとsubmitされて「戻る」ボタンが動いてしまうため。
//　エンターキーのキーコードは13
// inputのみを無効にします（textareaなどではエンターキーで改行したいため）

$(function() {
    $("input").on("keydown", function(ev) {
        if ((ev.which && ev.which === 13) || (ev.keyCode && ev.keyCode === 13)) {
            return false;
        } else {
            return true;
        }
    });
});