package com.locatemybus.myorderbox.Helper;

public class UserLocation {
//
//    private LocationManager locationManager = null;
//    private String provider = null;
//
//    private void locateCurrentPosition() {
//
//        int status =
//
//        if (status == PackageManager.PERMISSION_GRANTED) {
//
//            Location location = locationManager.getLastKnownLocation(provider);
//            updateWithNewLocation(location);
//            long minTime = 5000;// ms
//            float minDist = 5.0f;// meter
//            locationManager.requestLocationUpdates(provider, minTime, minDist, (LocationListener) this);
//        }
//    }
//
//    private boolean isProviderAvailable() {
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//        criteria.setAltitudeRequired(false);
//        criteria.setBearingRequired(false);
//        criteria.setCostAllowed(true);
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
//
//        provider = locationManager.getBestProvider(criteria, true);
//
//        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//            provider = LocationManager.NETWORK_PROVIDER;
//            return true;
//        }
//        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            provider = LocationManager.GPS_PROVIDER;
//            return true;
//        }
//        if (provider != null) {
//            return true;
//        }
//        return false;
//    }
//
//    private void updateWithNewLocation(Location location) {
//
//        if (location != null && provider != null) {
//            double lng = location.getLongitude();
//            double lat = location.getLatitude();
//
//            getAddress(location.getLatitude(),location.getLongitude());
//
//        } else {
//            Log.d("Location error", "Something went wrong");
//        }
//
//        return;
//    }
//
//    public void getAddress(double lat, double lng) {
//        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
//        try {
//            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
//            Address obj = addresses.get(0);
//            String   add = obj.getAddressLine(0);
//            String  currentAddress = obj.getSubAdminArea() + ","
//                    + obj.getAdminArea();
//            double   latitude = obj.getLatitude();
//            double longitude = obj.getLongitude();
//            String currentCity= obj.getSubAdminArea();
//            String currentState= obj.getAdminArea();
//            add = add + "\n" + obj.getCountryName();
//            add = add + "\n" + obj.getCountryCode();
//            add = add + "\n" + obj.getAdminArea();
//            add = add + "\n" + obj.getPostalCode();
//            add = add + "\n" + obj.getSubAdminArea();
//            add = add + "\n" + obj.getLocality();
//            add = add + "\n" + obj.getSubThoroughfare();
//
//            String pickup = preferences.getString("Pickup", null);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
