$('.ui.dropdown.newTenancy').dropdown('clear')

let latlong = [];
var map;
const markers = [];
var circle;

 function requestReport() {
  var center = circle.getCenter();
  var latcenter = center.lat().toString();
  var lngcenter = center.lng().toString();
  var radius = circle.getRadius().toString();
  //alert('radius');
  $('#radius').val(radius);
  $('#latcenter').val(latcenter);
  $('#lngcenter').val(lngcenter);
}

function initialize()
{
 retrieveMarkerLocations();
 
  var center = new google.maps.LatLng(53.347298, -6.268344);
  var initRadius = 10000;
  var mapProp = {
    center: center,
    zoom: 7,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
  };

  var mapDiv = document.getElementById('map_canvas');

  mapDiv.style.width = '100%';
  mapDiv.style.height = '500px';

    map = new google.maps.Map(mapDiv, mapProp);

  circle = new google.maps.Circle({
    center: center,
    radius: initRadius,
    strokeColor: '#0000FF',
    strokeOpacity: 0.4,
    strokeWeight: 1,
    fillColor: '#0000FF',
    fillOpacity: 0.4,
    draggable: true,
  });
  circle.setEditable(true);//allows varying radius be dragging anchor point
  circle.setMap(map);
  
}

/**
 * Use ajax call to get users and their geolocations
 * pass returned array marker locations to callback method
 * Here is the format in which marker data stored
 * geoObj[0] is descripion.
 * geoObj[1] is latitude
 * geoObj[2] is longitude
 * We use geoObj[0] in the infoWindow. Click marker to reveal description.
 */
function retrieveMarkerLocations()
{
  $(function () {
    $.get('/TenantData/retrieveVacantCord', function (data) {
      $.each(data, function (index, geoObj) {
        console.log(geoObj[0] + ' ' + geoObj[1] + ' ' + geoObj[2] + ' ' + geoObj[3] + ' ' + geoObj[4] + ' ' + geoObj[5]);
      });

      callback(data);
    });
  });
}

/**
 * ajax call locates the marker data
 * which is now put into an array
 * wit a format of (firstName, lat, lng)
 * 'fitBounds' is called to render the markers
 */
function callback(data)
{
  removeMarkers()
  latlng = data; // store the array of data in a global for later use
  fitBounds(latlng); // then invoke fitBounds to zoom and display markers
  setInfoWindowListener(latlng);
}

/**
 * position is created and allows zooming
 */
function fitBounds(latlngStr)
{
  var image = '/public/images/marker.png';
  const bounds = new google.maps.LatLngBounds();
  
  for (let i = 0; i < latlngStr.length; i++)
  {
    marker = new google.maps.Marker({
      position: getLatLng(latlngStr[i]),
      icon: image,
      map: map,

    });
    markers[i] = marker;
    bounds.extend(marker.position);
  }

  map.fitBounds(bounds);
  
}

function setInfoWindowListener(latlngStr)
{
  const infowindow = new google.maps.InfoWindow();
  for (let i = 0; i < latlng.length; i++)
  {
    /*respond to click on marker by displaying infowindow text*/
    const marker = markers[i];
    google.maps.event.addListener(marker, 'click', (function (marker, i) {
      return function () {
        infowindow.setContent('tenant: ' + latlngStr[i][3] + '<br />' + 'eircode: ' + latlngStr[i][4] + '<br />' + 'from: ' + latlngStr[i][5]);
        infowindow.open(map, marker);
      };
    })(marker, i));
  }
}

/**
 *A function that helps to convert string latlng to number afterwards to map object
 * @param str str is list of strings : username, lat, lon
 * latitude represents str[1]
 * longitude represents str[2]
 *
 * @param the str holds marker data which returns the google map location 
 */

function getLatLng(str)
{

  const lat = Number(str[1]);
  const lon = Number(str[2]);
  return new google.maps.LatLng(lat, lon);
}



function MarkersRemove()
{
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
}




google.maps.event.addDomListener(window, 'load', initialize);
