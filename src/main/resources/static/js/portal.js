// エンターキーを無効にする
// 無効にしておかないと登録画面で入力中にエンターキーを押下するとsubmitされて「戻る」ボタンが動いてしまうため。
window.onload = function() {
    document.onkeypress = enterForbidden;

    function enterForbidden() {
        if (window.event.keyCode == 13) {
            return false;
        }
    }
}