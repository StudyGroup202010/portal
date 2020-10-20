ポータルサイト
======================

## Overview  
社内で使うアプリケーションシステムです。

Note
-------
社内勉強会用に使います:sunglasses:

環境
-------
* git
  * Git for Windows (https://gitforwindows.org/)
  * TortoiseGit (https://tortoisegit.org/) (Language Packsも必要です）
* JDK
  * Adopt Open JDK 11  (https://adoptopenjdk.net/) 
* RDB  
  * PostgreSQL 12.4 (https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
* IDE
  * Spring Tool Suite 4.4.6.2 (https://spring.io/tools) ※Spring Tools 4 for Eclipseがお勧めです。  
* FrameWork
  * Spring
* RDBアクセス
  * JDBC、MyBatis 
* CSS
  * Bootstrap
* Build
  * Maven
* その他
  * Lombok (https://projectlombok.org/download)
  
【環境構築手順】  
１．GitHubアカウントを作成し、自PCにgitツールをインストールします。  
　　⇒アカウント参考（https://shimapuku.com/development/github-account )  
　　⇒ツール参考（https://qiita.com/SkyLaptor/items/6347f38c8c010f4d5bd2  )  
　　※作業手順は公開鍵の登録まででOKです。  
２．JDKをインストールします。  
　　⇒参考（https://www.javadrive.jp/start/install/index6.html ）  
３．RDBをインストールします。  
　　⇒参考（https://eng-entrance.com/postgresql-download-install ）  
４．IDEをインストールします。  
　　⇒参考（https://techfun.cc/spring/windows-spring-install.html  )  
  　　日本語化（https://nobuzii.hatenablog.com/entry/2020/03/17/002607 )  
５．Lombokをインストールします。  
　　⇒参考（ https://qiita.com/r_saiki/items/82231ded1450f5ed5671 )  
６．自GitHubアカウントにStudyGroup202010アカウントのportalリポジトリをforkしてください。  
　　⇒参考（https://denno-sekai.com/github-fork/ ）  
７．自PCに作業用フォルダを作り、自GitHubアカウントにforkしたリポジトリをクローン（複製）してください。  
　　⇒参考（https://pasomaki.com/tortoisegit-clone/ ）  
　　【注意】クローンするのはmasterブランチではなくtopicブランチを使ってください。  
８．自PCにクローンしたリポジトリのenvironmentフォルダのSQLを使ってRDBにDBとTableを作成してください。  
　　※テストデータも追加してください。  
９．IDEにリポジトリをインポートしてコンパイルし、ブラウザから「http://localhost:8080/login」 を起動してください。  
　　⇒参考（https://bit.ly/3iTNHSk ）  
　　※インポートするときの「ルートディレクトリ」は、クローンした「portal」フォルダを指定してください。  

アプリケーション構成
-------
com   
 └ portal  
　　├ z  ・・・　全体の共通機能  
　　│　├ common  ・・・zの共通機能  
　　│　│　├ aspect  
　　│　│　├ config  
　　│　│　├ controller  
　　│　│　└ domain  
　　│　│　　├ model  
　　│　│　　├ repository  
　　│　│　　└ service  
　　│　└ 〇〇〇  ・・・zの個別機能（〇〇画面など）  
　　│　　　├ controller  
　　│　　　└ domain  
　　│　　　　　├ model  
　　│　　　　　├ repository  
　　│　　　　　└ service  
　　│  
　　├ a  ・・・アプリケーションＡ  
　　│　├ common  ・・・Ａの共通機能  
　　│　│　├ config  
　　│　│　├ controller  
　　│　│　└ domain  
　　│　└ 〇〇〇  ・・・Ａの個別機能  
　　│　　├ controller  
　　│　　└ domain  
　　│  
　　└ b  ・・・アプリケーションＢ     
 
 ブランチ運用ルール  
-------
ブランチ運用ルールはGitHubFlowにします。（https://engineer-life.dev/git-operation-rule/)  

（イメージ）   
　◆StudyGroup202010  
 　　└ portal  
　　　　├ masterブランチ  
　　　　├ TopicブランチＡ   
　　　　└ TopicブランチＢ 
    
　　:arrow_down:(Fork)　　:arrow_up:（PullRequest)  
     
　◆開発者アカウント  
 　　└ portal  
　　　　├ masterブランチ  
　　　　├ TopicブランチＡ   
　　　　└ TopicブランチＢ 
    
　　:arrow_down:(Clone)　　:arrow_up:（Push)  
    
　◆開発者ローカルＰＣ  
 　　└ portal  
　　　　└ TopicブランチＢ   


１．課題対応用に、管理者がStudyGroup202010にTopicブランチを切ります。（#34_202010など）  
２．開発者が、開発者ローカルＰＣにそれをCloneして実装します。  
３．テストまで済んだら開発者アカウントにPushし、StudyGroup202010にPullRequestを投げます。  
４．管理者のレビューが済んだら、管理者がmasterブランチにマージします。  

デプロイ
-------
Heroku（https://jp.heroku.com/home ）にデプロイします。    
　⇒参考（https://qiita.com/sho7650/items/ebd87c5dc2c4c7abb8f0　)  
　　※準備２以降が該当します。  
　　※Herokuに作成したアプリケーションにBuildpacks(java)を追加する必要があります。  

　デプロイ後のURL　⇒　https://portalemd.herokuapp.com/　  
 

開発の進め方  
-------  
１．開発基盤の整理（共通部品やひな形など開発に必要なものを揃えます）:point_left:イマココ  
２．業務機能開発（業務で使う画面などを開発します）  


References
-------
* こちらのサイトが参考になります。⇒　<https://terasolunaorg.github.io/guideline/5.6.0.RELEASE/ja/>  
* 書籍はこちら⇒【後悔しないための入門書】Spring解体新書（https://amzn.to/3jDEaQy)  


Contributing
-------
１．システムを動かしてみて、不具合を見つけたり、追加したい機能が浮かんだら、イシューを追加してください:hand:  
２．イシューの中から自分ができそうなものを選んで、自PCにクローンしたリポジトリに実装してみてください:computer:   
３．実装してテストをしたら、プルリクエストを送ってください。レビューしてmasterブランチにマージします:thumbsup:  


Authors
----------
Copyright &copy; 〇〇〇
  
License
----------
Distributed under the [MIT License][mit].
 
[MIT]: http://www.opensource.org/licenses/mit-license.php
