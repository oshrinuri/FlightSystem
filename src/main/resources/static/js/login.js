$(function() {
    if ($('#register-error-alert').length) {
      $('#registerModal').modal('show');
    }
});

function showLoadingSpinner(view,reset_button,submit_button) {
  $("#loading-icon").show();
  $("#background-wrap").css("background-color","#FFFFFF");
  $("#background-wrap").css("opacity","0.5");
  if (view != null) view.css("pointer-events","none");
  if (reset_button != null) reset_button.attr("disabled",true);
  if (submit_button != null) submit_button.attr("disabled", true);
}

$("#login-form").submit(function(){
  showLoadingSpinner($(".container-login100"),$("#resetButton"),$("#submitButton"))
});

$('#register-password').password({
  enterPass: '',
  shortPass: 'The password is too short',
  containsField: 'The password contains your username',
  steps: {
    13: 'Really insecure password!',
    33: 'Weak; try combining letters & numbers.',
    67: 'Medium; try using special characters.',
    94: 'Strong password.',
  },
  showPercent: false,
  showText: true,
  animate: true, 
  animateSpeed: 'fast',
  field: false,
  fieldPartialMatch: true, 
  minimumLength: 6, 
  useColorBarImage: true, 
  customColorBarRGB: {
    red: [0, 240],
    green: [0, 240],
    blue: 10,
  } 
});

$("#register-form").submit(function() {
  if (checkPasswordMatch()) {
    showLoadingSpinner(null,null,null);
    $('#registerModal').modal('hide');
    return true;
  }
  return false;
})

function checkPasswordMatch() {
  var password = $("#register-password").val();
  var confirmPassword = $("#register-password-conf").val();
  if (password != confirmPassword) {
      $(".pass-text").html("Passwords does not match!");
      return false;
  }
  return true;
}