var express = require('express');
var fs = require('fs');
var app = express();
var mysql = require('mysql');
var static = require('serve-static');
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '12345',
  port     : '3306',
  database : 'test'
});
var query;
connection.connect();
var path = require('path');
app.use(static(path.join(__dirname,'public')));

app.get('/', function(req, res){
    res.send('Hello home page');
});
app.get('/route', function(req, res){
    res.send('Hello Router, <img src="/image.bmp">');
});
app.get('/login', function(req, res){
    res.send('<h1>Login please</h1>');
});
app.get('/users', function(req, res) {n
	connection.query('SELECT url from pic order by id DESC limit 1 ', function(err, rows, fields) {
   query = rows;
      
});
   res.json(query);
});
app.listen(8080, function(){
    console.log('Conneted 8080 port!');
});
