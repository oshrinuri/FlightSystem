$(function() {
    $("#login-form").submit(function(){
      $("#loading-icon").show();
      $("#background-wrap").css("background-color","#FFFFFF");
      $("#background-wrap").css("opacity","0.5");
      $(".container-login100").css("pointer-events","none");
      $("#submitButton").attr("disabled",true);
      $("#resetButton").attr("disabled", true);
    });
});