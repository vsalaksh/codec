var express = require('express');
var app = express();

var routers = require('./routers.js');

//both index.js and things.js should be in same directory
app.use(express.static('public'));
app.use('/', routers);
app.set('view engine', 'pug');
app.set('views','./views');
//var cluster = require('cluster');
//var numCPUs = 4;
//if (cluster.isMaster)
//{
//for (var count=0; count< numCPUs; count++) {
//        cluster.fork();
//    }
//} 
//else
//{
//app.listen(80);
//}
app.listen(3000);