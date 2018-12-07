$(function(){

  $('#button').click(function(){
      var response = '';
	  var input = $('#input').val();
	  if (!input)
	  {
	  alert('Provide valid Input String');
	  return;
	  }
	  $.ajax({ type: "POST",   
	  url: $('#endurl').val(),
	  crossDomain: true,
	  contentType: "application/json",
	   headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
	     dataType : "json",   
         data: JSON.stringify({"input":$('#input').val()}),
		 success : function(text)
         {
             //alert(text.output);
			 response = text.output;
			 $('#output').val(response);
         },
		 error : function(text, status, error)
		 {
		 alert(error);
		 }
});
});

$('#jwtDecodeButton').click(function(){
      
	  var response = '';
	  //alert('hi');
	  var input = $('#jwttokeninput').val();
	  var splitToken = input.split(".");
	  var jwtHeaderPayload = '';
	  var jwtSignature = '';
	  $("#validSignatureMessage").text('');
	  $("#signatureErrorMessage").text('');
	  
	  if (splitToken.length > 1)
	  {
	  jwtHeaderPayload = splitToken[0] + "." + splitToken[1];
	  if (splitToken.length > 2)
	  {
	  jwtSignature = splitToken[2];
	  }
	  }
	  else
	  {
	  alert('Not a Valid JWT Token');
	  document.getElementById('jwtHeader').innerHTML = '';
      document.getElementById('jwtBody').innerHTML = '';
	  return;
	  }
	  $('#jwtHeader').html('<label class="loader" id="loader-4" style="height:40px"><span></span><span></span><span></span>Decoding JWT Header in progresss</label>');
      $('#jwtBody').html('<label class="loader" id="loader-4"><span></span><span></span><span></span>Decoding JWT Body in progresss</label>');
 
	  //alert('hi');
	  var jwtSigningAlg = '';
	  $.ajax({ type: "POST",   
	  url: $('#endurl').val(),
	  crossDomain: true,
	  contentType: "application/json",
	   headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
	     dataType : "json",   
         data: JSON.stringify({"input":$('#jwttokeninput').val()}),
		 success : function(response)
         {
             //alert(text.output);
			 token = response.output;
			 $('#output').val(token);
			 //alert(response.decodedJWTHeader);
			 jwtHeaderVal = response.decodedJWTHeader;
			 jwtBodyVal = response.decodedJWTBody;
			 jwtSigningAlg = response.alg;
			 //jwtHeaderVal1 = jwtHeaderVal.replace(/\n/g, "<br/>");
			 document.getElementById('jwtHeader').innerHTML = jwtHeaderVal;
			 document.getElementById('jwtBody').innerHTML = jwtBodyVal;
			 $('#textoutput').val(token);
			 $('#jwtHeaderPayload').val(jwtHeaderPayload);
			 $('#jwtSignature').val(jwtSignature);
			 $('#jwtSigningAlg').val(jwtSigningAlg);
			 if (jwtSigningAlg.startsWith('HS'))
			 {
			 $('#signingKey').val('Enter Shared Secret');
			 }
			 else
			 {
			 $('#signingKey').val('Enter Base64 Encoded Public Key');
			 }
         },
		 error : function(text, status, error)
		 {
		 alert(text.errorMessage);
		 document.getElementById('jwtHeader').innerHTML = 'Decoding Failed';
         document.getElementById('jwtBody').innerHTML = 'Decoding Failed';
		 }
});
});

 $('#format').click(function(){
 var response = '';
	  var input = $('#input').val();
	  if (!input)
	  {
	  alert('Provide valid Input');
	  return;
	  }
	  var endURL = $('#endurl').val();
	  //alert(input + endURL);
	  $.ajax({ type: "POST", url: $('#endurl').val(), crossDomain: true, contentType: "application/json", 
	   headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
	     dataType : "json",   
         data: JSON.stringify({"input":$('#input').val()}),
		 success : function(text)
         {
             $("pre").removeClass("prettyprinted");
			 response = text.output;
			 $('#textoutput').val(response);
			 //$('#output').val(response);
			 response = response.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
			 //response = response.replace(/&lt;script src[\\s\S]*?&gt;&lt;\/script&gt;|&lt;!--\?[\s\S]*?--&gt;|&lt;pre\b[\s\S]*?&lt;\/pre&gt;/g, '<span class=operative>$&</span>');
			 
			 //document.getElementById("formatted").innerHTML = response;
			 $('#output').html(response);
			 $('#textoutput').html(response);
			 PR.prettyPrint();
			 
         },
		 error : function(text, status, error)
		 {
		 alert(error);
		 }
		 
 
 });
 });
 
 $('#decompile').click(function(){
 $('#formatted').html('<div class="container"><div class="row"><div class="col-md-3 bg"> Decompiling in progresss<div class="loader" id="loader-6"><span></span><span></span><span></span></div></div></div>');
 var response = '';
	  //alert('Clicked Decompile');
	  var formval = new FormData();
jQuery.each(jQuery('#classfile')[0].files, function(i, file) {
    formval.append('file', file);
	$('#filename').val(file.name.split('.')[0] + '.java');
	return false;
	//alert(file.name.split('.')[0]);
});
	  $.ajax({ type: "POST", url: $('#endurl').val(), crossDomain: true, contentType: false, processData: false, 
	    data: formval,
		 success : function(value)
         {
             alert('Decompiled' + value);
			 response = value;
			 $('#output').val(response);
			 $('#textoutput').val(response);
			 response = response.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
			 $('#formatted').html(response);
			 $("body").append("<iframe src='" + text.url+ "' style='display: none;' ></iframe>");
			 
         },
		 error : function(text, status, error)
		 {
		 $('#formatted').html('<h4>If you get this error message, this could be due to double click on the open file window. Instead of double click, select the file first and then click open button</h4>');
		 }
 
 });
 });
 
 $("#classfile").change(function () {

  var fieldVal = $(this).val();

  // Change the node's value by removing the fake path (Chrome)
  fieldVal = fieldVal.replace("C:\\fakepath\\", "");

  $("#subfile").val(fieldVal);

});
 $('#copyBtn').click(function(){
  var copyText = document.getElementById("output");
 //alert($('#textoutput').val());
copyText.select();

 document.execCommand("copy");

 });
 $('#copyFormattedBtn').click(function(){
  var copyText = document.getElementById("textoutput").value;
 var tempInput = document.createElement("textarea");
    tempInput.style = "position: absolute; left: -1000px; top: -1000px";
    tempInput.value = copyText;
    document.body.appendChild(tempInput);
    tempInput.select();
    
    
 //alert(copyText);
 document.execCommand("copy");
 document.body.removeChild(tempInput);

 });
 $('#downloadBtn').click(function(){
 var textToWrite = document.getElementById("textoutput").value;
 
    var textFileAsBlob = new Blob([textToWrite], {type:'text/plain'});
	var fileType = '';
    var fileNameToSaveAs = $('#filename').val();;

    var downloadLink = document.createElement("a");
    downloadLink.download = fileNameToSaveAs;
    downloadLink.innerHTML = "Download File";
    if (window.webkitURL != null)
    {
        // Chrome allows the link to be clicked
        // without actually adding it to the DOM.
        downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
    }
    else
    {
        // Firefox requires the link to be added to the DOM
        // before it can be clicked.
        downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
        downloadLink.onclick = destroyClickedElement;
        downloadLink.style.display = "none";
        document.body.appendChild(downloadLink);
    }

    downloadLink.click();
 });
 
 $('#verifySignature').click(function(){
 $("#validSignatureMessage").text('');
 $("#signatureErrorMessage").text('');
 var response = '';
	  var input = $('#input').val();
	  var endURL = $('#endurl').val();
	  //alert(input + endURL);
	  $.ajax({ type: "POST", url: $('#signatureVerificationEndPoint').val(), crossDomain: true, contentType: "application/json", 
	   headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
	     dataType : "json",   
         data: JSON.stringify({"payload":$('#jwtHeaderPayload').val(), "signature":$('#jwtSignature').val(), "base64EncodedKey":$('#signingKey').val(), "alg":$('#jwtSigningAlg').val(), "sharedSecret":$('#signingKey').val()}),
		 success : function(text)
         {
            //alert('Success');
			if(text.valid)
			{
			$("#validSignatureMessage").text('Signature Verified, Its Valid')
			}
			else
			{
			$("#signatureErrorMessage").text('Signature is not valid');
			}
			 
         },
		 error : function(text, status, error)
		 {
		 alert(error);
		 }
		 
 
 });
 });
 
 $('#input').on('paste', function(e) {
setTimeout(function(){ //break the callstack to let the event finish

    //alert($('#input').val()); //read the value of the input field   
$("#button").click();
$("#format").click();
$("#jwtDecodeButton").click();
  },0); 
 
   
});	

$('#jwttokeninput').on('paste', function(e) {
setTimeout(function(){ //break the callstack to let the event finish

    //alert($('#input').val()); //read the value of the input field   
//$("#button").click();
//$("#format").click();
$("#jwtDecodeButton").click();
  },0); 
 
   
});	

$('#jwthelp').click(function(){ //you can give id or class name here for $('button')
    $(this).text(function(i,old){
        return old=='Hide' ?  'Help' : 'Hide';
    });
});

$('#samplejwt').click(function(){ 
$('#jwttokeninput').val('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c');
$("#jwtDecodeButton").click();

});

$('#compressjpeg').click(function(){
 $('#formatted').html('<div class="container"><div class="row"><div class="col-md-3 bg"> Compressing in progresss<div class="loader" id="loader-6"><span></span><span></span><span></span></div></div></div>');
 var response = '';
	  //alert('Clicked Decompile');
	  var formval = new FormData();
jQuery.each(jQuery('#classfile')[0].files, function(i, file) {
    formval.append('file', file);
	$('#filename').val(file.name.split('.')[0] + '.java');
	return false;
	//alert(file.name.split('.')[0]);
});
	  $.ajax({ type: "POST", url: $('#endurl').val(), crossDomain: true, contentType: false, processData: false, 
	    data: formval,
		 success : function(text)
         {
             $("pre").removeClass("prettyprinted");
			 response = text.decompiledCode;
			 $('#output').val(response);
			 $('#textoutput').val(response);
			 response = response.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
			 //response = response.replace(/&lt;script src[\\s\S]*?&gt;&lt;\/script&gt;|&lt;!--\?[\s\S]*?--&gt;|&lt;pre\b[\s\S]*?&lt;\/pre&gt;/g, '<span class=operative>$&</span>');
			 
			 //document.getElementById("formatted").innerHTML = response;
			 if (response.includes('ERROR'))
			 {
			 response='Error while decompiling the file';
			 }
			 $('#formatted').html(response);
			 PR.prettyPrint();
			 
         },
		 error : function(text, status, error)
		 {
		 $('#formatted').html('<h4>If you get this error message, this could be due to double click on the open file window. Instead of double click, select the file first and then click open button</h4>');
		 }
 
 });
 });
});
