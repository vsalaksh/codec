var express = require('express');
var router = express.Router();

router.get('/', function(req, res){
res.redirect('/decoder/url');
});


router.get('/decoder/url', function(req, res){
   res.render('codec', {
      name: "URL Decoder", 
      btnName:"Decode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/decoder/url",
	  title: "URL Decoder - Best Online URL Decoding Tool",
	  content: "Decodes the url endoded string, in to the original string. ",
	  metaContent: "Best Online URL Decoder for converting the url encoded string to the original String"
	  
   });
});
router.get('/encoder/url', function(req, res){
   res.render('codec', {
      name: "URL Encoder", 
      btnName:"Encode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/encoder/url",
	  title: "URL Encoder - Best Online URL Encoding Tool",
	  content: "Only ASCII character-set are allowed in the URL. For any other characters to be part of the url, first it should be encoded such that any unsafe ASCII characters are replaced with a followed by two hexadecimal digits.Also URLs cannot contain spaces. URL encoding normally replaces a space with a plus (+) sign or with %20. The process of converting a string in to valid url format is called URL Encoding or Percentage Encoding",
	  metaContent: "Best Online URL Encoder for converting the input url and query parameters in to URL Encoded format. This is also called as Percentage Encoding"
	  
   });
});
router.get('/decoder/b64', function(req, res){
   res.render('codec', {
      name: "Base64 Decoder", 
      btnName:"Decode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/decoder/b64",
	  title: "Base64 Decoder - Best Online Base64 Decoding Tool",
	  content: "Decodes the base64 encoded string in to original data",
	  metaContent: "Base64 Decoder helps you to convert the base64 encoded string in to the original value"
   });
});
router.get('/encoder/b64', function(req, res){
   res.render('codec', {
      name: "Base64 Encoder", 
      btnName:"Encode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/encoder/b64",
	  title: "Base64 Encoder - Best Online Base64 Encoding Tool",
	  content: "Base64 is a way to encode binary data into an ASCII character set known to pretty much every computer system, in order to transmit the data without loss or modification of the contents itself. For example, mail systems cannot deal with binary data because they expect ASCII (textual) data. So if you want to transfer an image or another file, it will get corrupted because of the way it deals with the data. Note: base64 encoding is NOT a way of encrypting, nor a way of compacting data. In fact a base64 encoded piece of data is 1.333… times bigger than the original datapiece. It is only a way to be sure that no data is lost or modified during the transfer.",
	  metaContent: "Base64 Encoder used for converting the input value to base64 encoded format"
   });
});
router.get('/decoder/jwt', function(req, res){
   res.render('codec', {
      name: "JWT Decoder", 
      btnName:"Decode",
	  serviceEndPoint:"http://localhost:8080/coolblue/webapi/decoder/jwt",
	  title: "JWT Decoder , Online JSON Web Token Decoder",
	  content: "Decodes the input in to JWT Token",
	  metaContent: "A Simple online decoder tool for JWT token, parses the jwt token and print it in a user readable format"
   });
});

//Formatter Module Configuration

router.get('/formatter/json', function(req, res){
   res.render('formatter', {
      name: "JSON Formatter", 
      btnName:"Format",
	  serviceEndPoint:"http://localhost:8080/formatter/webapi/formatter/json",
	  title: "JSON Formatter - Parses the JSON String and print it in a user friendly readable format",
	  content: "A simple online tool Parses the JSON String and print it in a user friendly readable format.",
	  metaContent: "A simple online JSON Formatter, parses the JSON String and print it in a user friendly readable format."
   });
});
router.get('/formatter/xml', function(req, res){
   res.render('formatter', {
      name: "XML Formatter", 
      btnName:"Format",
	  serviceEndPoint:"http://localhost:8080/formatter/webapi/formatter/xml",
	  title: "Free Online XML Formatter , formats the html string in a user friendly format",
	  content: "Tired of reading big complex XML? Just enter the string below, tool formats the input string in to a more readable XML format",
	  metaContent: "A simple online XML Formatter, parses the XML String and print it in a user friendly readable format."
   });
});
router.get('/formatter/html', function(req, res){
   res.render('formatter', {
      name: "HTML Formatter", 
      btnName:"Format",
	  serviceEndPoint:"http://localhost:8080/formatter/webapi/formatter/html",
	  title: "Free Online HTML Formatter , formats the html string in a user friendly format",
	  content: "Tired of reading big complex html? Just enter the string below, tool formats the input string in to a more readable html format",
	  metaContent: "A simple online HTML Formatter, parses the HTML String and print it in a user friendly readable format."
   });
});
router.get('/formatter/css', function(req, res){
   res.render('formatter', {
      name: "CSS Formatter", 
      btnName:"Format",
	  serviceEndPoint:"http://localhost:8080/formatter/webapi/formatter/css",
	  title: "JWT Decoder , Online JSON Web Token Decoder",
	  content: "Decodes the input in to JWT Token"
   });
});
router.get('/formatter/javascript', function(req, res){
   res.render('formatter', {
      name: "JavaScript Formatter", 
      btnName:"Format",
	  serviceEndPoint:"http://localhost:8080/formatter/webapi/formatter/javascript"
	  
   });
});
router.get('/decompiler/java', function(req, res){
   res.render('decompiler', {
      name: "Java Decompiler", 
      btnName:"Decompile",
	  serviceEndPoint:"http://localhost:8080/decompiler/webapi/decompiler/java",
	  title: "Free Online Java Decompiler , Decompiles class files to java files, Helps on how to decompile class file",
	  content: "Best Online Java Decompiler , decompiles your class files in to java files in a single click. Java decompiler online, Select the class file and click Decompile",
	  metaContent: "Best Online Java Decompiler , decomiles your class files in to java files in a single click."
	  
   });
});

//export this router to use in our index.js
module.exports = router;