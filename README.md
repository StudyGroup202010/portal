ポータルサイト
======================

## Overview  
社内で使うアプリケーションシステムです。

Note
-------
社内勉強会用に使います。

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
  * Spring Tool Suite 4.4.6.2 (https://spring.io/tools) 
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
１．GitHubアカウントを作成し、ＰＣにgitツールをインストールします。  
　　⇒参考（https://qiita.com/SkyLaptor/items/6347f38c8c010f4d5bd2  )
２．JDKをインストールします。  
３．RDBをインストールします。  
４．IDEをインストールします。  
５．自分のGitHubアカウントにStudyGroup202010アカウントのportalリポジトリをforkしてください。  
６．ＰＣに作業用フォルダを作り、自アカウントにforkしたリポジトリをクローン（複製）してください。  
　　⇒masterではなくブランチを使ってください。  
７．クローンしたリポジトリのenvironmentフォルダにあるSQLで、DBを作成してください。  
　　⇒DatabaseとTable、テストデータがあります。   
８．IDEにリポジトリを取り込んでコンパイルし、ブラウザから「http://localhost:8080/login」 を起動してください。  

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
 
References
-------
* こちらのサイトが参考になります。⇒　<https://terasolunaorg.github.io/guideline/5.6.0.RELEASE/ja/>  
* 書籍はこちら⇒【後悔しないための入門書】Spring解体新書（https://amzn.to/3jDEaQy)  


Contributing
-------
１．システムを動かしてみて、不具合を見つけたり、追加したい機能が浮かんだら、イシューを追加してください。  
２．イシューの中から、自分ができそうなものを選んで、実装してみてください。  
　　⇒実装するときは自分のGithubアカウントにリポジトリをforkして、masterではなくブランチを使ってください。  
３．実装してテストをしたら、プルリクエストを送ってください。レビューしてmasterにマージします。  


Authors
----------
Copyright &copy; 〇〇〇
  
License
----------
Distributed under the [MIT License][mit].
 
[MIT]: http://www.opensource.org/licenses/mit-license.php
