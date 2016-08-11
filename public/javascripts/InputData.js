/* initialize the controls in the inputdata page and validate residence type(dropdown)*/

$('.ui.dropdown').dropdown();
$('.ui.checkbox').checkbox();

$('.ui.form').form({
  eircode: {
    identifier: 'eircode',
    rules: [{
      type: 'empty',
      prompt: 'Please enter eircode'
    }]
  },
  rent: {
    identifier: 'rent',
    rules: [{
      type: 'empty',
      prompt: 'Please enter rent'
    }]
  },
  numbOfBedrooms: {
    identifier: 'numbOfBedrooms',
    rules: [{
      type: 'empty',
      prompt: 'Please enter number of bedroom'
    }]
  },
  numberBathrooms: {
    identifier: 'numberBathrooms',
    rules: [{
      type: 'empty',
      prompt: 'Please enter number of bathroom'
    }]
  },
  area: {
    identifier: 'area',
    rules: [{
      type: 'empty',
      prompt: 'Please enter the area'
    }]
  },
  residenceType: {
    identifier: 'residenceType',
    rules: [{
      type: 'empty',
      prompt: 'Please select a residence type'
    }]
  }

});