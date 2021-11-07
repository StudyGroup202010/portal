// 天気の画像を取得する
async function getWeatherImageUrl() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
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

// 天気の詳細を取得する
async function getWeatherDetail() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.forecasts[0].detail.weather;
    });
    
    var elem = document.getElementById("forecastDetail");
    elem.innerHTML = data;
}

// 天気の最高気温を取得する
async function getWeatherTempMax() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.forecasts[0].temperature.max.celsius;
    });
    
    var elem = document.getElementById("forecastTempMax");
    elem.innerHTML = data;
}

// 天気の最低気温を取得する
async function getWeatherTempMin() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.forecasts[0].temperature.min.celsius;
    });
    
    var elem = document.getElementById("forecastTempMin");
    elem.innerHTML = data;
}

// 天気の降水確率（午前）を取得する
async function getWeatherChanceOfRainAM() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.forecasts[0].chanceOfRain.T06_12;
    });
    
    var elem = document.getElementById("forecastChanceOfRainAM");
    elem.innerHTML = data;
}

// 天気の降水確率（午後）を取得する
async function getWeatherChanceOfRainPM() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.forecasts[0].chanceOfRain.T12_18;
    });
    
    var elem = document.getElementById("forecastChanceOfRainPM");
    elem.innerHTML = data;
}

// 天気の降水確率（夜）を取得する
async function getWeatherChanceOfRainNT() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.forecasts[0].chanceOfRain.T12_18;
    });
    
    var elem = document.getElementById("forecastChanceOfRainNT");
    elem.innerHTML = data;
}

// 予報の発表日時を取得する
async function getWeatherTime() {
    var url =  'https://weather.tsukumijima.net/api/forecast/city/210010'; //210010が岐阜
    let data =  await fetch(url)
        .then(function (data) {
            return data.json();
        })
    .then(function (json) {
        return json.publicTimeFormatted;
    });
    
    var elem = document.getElementById("publicTimeFormatted");
    elem.innerHTML = data;
}

