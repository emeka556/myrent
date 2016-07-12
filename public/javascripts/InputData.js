// initialize the controls in the input data template and validate residence type
$('.ui.dropdown').dropdown();
$('#progress').progress();
$('.ui.checkbox').checkbox();
$('.ui.form')
.form({
  residenceType : {
    identifier : 'residenceType',
    rules: [
      {
          type : 'empty',
          prompt: 'Please select a residence type'
      }
    ]
  }
});