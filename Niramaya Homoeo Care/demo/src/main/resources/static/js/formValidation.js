
function validateName()
{
	   if(!document.getElementById("name").value.match(/^[A-Za-z ]+$/))  
	  {
		   if(document.getElementById("name").value)
			   document.getElementById("namevalidator").innerHTML="Please Key in a Valid Name.";
		   else
			   document.getElementById("namevalidator").innerHTML="Name Field Empty!";
	  }
	   else
		   document.getElementById("namevalidator").innerHTML="";
	   
}

function validateMobileNumber()
{
	if(!(document.getElementById("number").value.length==10 && parseInt(document.getElementById("number").value)>=6000000000 && parseInt(document.getElementById("number").value)<=9999999999))
	{
		if(document.getElementById("number").value)
			   document.getElementById("mobilenumbervalidator").innerHTML="Please Key in a Valid Mobile Number.";
		   else
			   document.getElementById("mobilenumbervalidator").innerHTML="Mobile Number Field Empty!";
	}
	else
		document.getElementById("mobilenumbervalidator").innerHTML="";
	
}

function showToast()
{
if((document.getElementById("number").value.length==10 && parseInt(document.getElementById("number").value)>=6000000000 && parseInt(document.getElementById("number").value)<=9999999999) && (document.getElementById("name").value.match(/^[A-Za-z ]+$/)) && document.getElementById("concerns").value )
{ 
  var x = document.getElementById("snackbar");
  x.innerText += "Thank you "+document.getElementById("name").value+" for Reaching out to NIRAMAYA HOMOEO CARE. Our Chief Doctor would reach out to you soon enough in regard to your concerns. ";
  x.setAttribute("style", "left:150px;");
  x.className = "show-bar";
  setTimeout(function(){x.className = x.className.replace("show-bar", ""); }, 3000);
  document.getElementById("baseurl").value=window.location.origin;
  setTimeout(function(){ document.forms[0].submit();document.forms[0].reset(); }, 3000);
  
}

else
{
  var x = document.getElementById("snackbar");
  x.setAttribute("style", "left:450px;");
  x.innerText += "Please Key in Valid Data.";
  x.className = "show-bar";
  setTimeout(function(){x.className = x.className.replace("show-bar", ""); }, 3000);	
}

}