import java.util.*;

/**
 * An object class for creating and comparing Institute objects. Institute objects are compared using first
 * Institution names, and then unit ID's. It is assumed that no two objects will have the same name and ID.
 * @author Mason Clarke
 * @date Feb 14, 2021
 */ 
public class Institute{

  //Initialize instance variables
  long unitId;
  String institutionName;
  String city;
  String stateAbreviation;
  String zip;
  String accredagency;
  String institutionURL;
  double latitude;
  double longitude;
  String ccUndergradProfile; // cc stands for carnegie classification

  // A constructor setting all instance variables
  public Institute(long unitId, String institutionName, String city,
  	String stateAbreviation, String zip, String accredagency, String institutionURL,
    double latitude, double longitude, String ccUndergradProfile) {
  	this.unitId = unitId;
  	this.institutionName = institutionName;
  	this.city= city;
  	this.stateAbreviation = stateAbreviation;
  	this.zip = zip;
  	this.accredagency = accredagency;
  	this.institutionURL = institutionURL;
  	this.latitude = latitude;
  	this.longitude = longitude;
  	this.ccUndergradProfile = ccUndergradProfile;
  }

  // CREATE Getters and Setters for each instance variable HERE
  public long getUnitId() { return unitId; }

  public void setUnitId(long unitId) { this.unitId = unitId; }

  public String getInstitutionName() { return institutionName; }

  public void setInstitutionName(String institutionName) { this.institutionName = institutionName; }

  public String getCity() { return city; }

  public void setCity(String city) { this.city = city; }

  public String getStateAbreviation() { return stateAbreviation; }

  public void setStateAbreviation(String stateAbreviation) { this.stateAbreviation = stateAbreviation; }

  public String getZip() { return zip; }

  public void setZip(String zip) { this.zip = zip; }

  public String getAccredagency() { return accredagency; }

  public void setAccredagency(String accredagency) { this.accredagency = accredagency; }

  public String getInstitutionURL() { return institutionURL; }

  public void setInstitutionURL(String institutionURL) { this.institutionURL = institutionURL; }

  public double getLatitude() { return latitude; }

  public void setLatitude(double latitude) { this.latitude = latitude; }

  public double getLongitude() { return longitude; }

  public void setLongitude(double longitude) { this.longitude = longitude; }

  public String getCcUndergradProfile() { return ccUndergradProfile; }

  public void setCcUndergradProfile(String ccUndergradProfile) { this.ccUndergradProfile = ccUndergradProfile; }

  /**
   * Method for comparing two Institute objects for the purpose of sorting. Comparisons are based first on
   * institutionName, and in the case of a tie then on unitId.
   * @param institute2 Institute object to be compared to the given object
   * @return return 1 if the first Institute object is lexicographically or numerically larger than the second, and -1
   * if the opposite is true.
   */
  public int compareTo(Institute institute2) 
  {
    if (this.institutionName.compareTo(institute2.institutionName) > 0){
      return 1;
    } else if (this.institutionName.compareTo(institute2.institutionName) < 0) {
      return -1;
    } else if (this.institutionName.equals(institute2.institutionName) && this.unitId < institute2.unitId){
  	  return -1;
    }
    return 1;
  }
}
