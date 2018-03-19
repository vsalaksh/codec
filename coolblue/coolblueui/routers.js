var express = require('express');
var router = express.Router();

router.get('/decoder/url', function(req, res){
   res.render('codec', {
      name: "URL Decoder", 
      btnName:"Decode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/decoder/url"
	  
   });
});
router.get('/encoder/url', function(req, res){
   res.render('codec', {
      name: "URL Encoder", 
      btnName:"Encode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/encoder/url"
	  
   });
});
router.get('/decoder/b64', function(req, res){
   res.render('codec', {
      name: "Base64 Decoder", 
      btnName:"Decode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/decoder/b64"
   });
});
router.get('/encoder/b64', function(req, res){
   res.render('codec', {
      name: "Base64 Encoder", 
      btnName:"Encode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/encoder/b64"
   });
});
router.get('/decoder/jwt', function(req, res){
   res.render('codec', {
      name: "JWT Decoder", 
      btnName:"Decode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/decoder/jwt"
   });
});

//export this router to use in our index.js
module.exports = router;