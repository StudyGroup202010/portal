// 岐阜の天気の画像を取得する
async function getWeatherImageUrl() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/230010'; //230010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.forecasts[0].image.url;
    });
    
    var elem = document.getElementById("forecastImage");
    elem.src = data;
}
