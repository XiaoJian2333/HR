package com.xiaojian.model;

import java.util.Date;

public class Staff {
	private int id;
	private String StaffNum;
	private int positionNameId;
	private int titleId;
	private String name;
	private String sex;
	private String email;
	private String phone;
	private String qq;
	private String mobile;
	private String address;
	private String zipCode;
	private String nationality;
	private String birthPlace;
	private Date birthDate;
	private String ethnicity;
	private String religiousBelief;
	private String politicalAffiliation;
	private String idCard;
	private String socialSecurityNumber;
	private int age;
	private String education;
	private int educationYear;
	private String educationMajor;
	private int institutionId;
	private String bank;
	private String bankNum;
	private String registrant;
	private Date registrationTime;
	private String specialty;
	private String hobby;
	private String personalResume;
	private String familyRelationships;
	private String remark;
	private String state;
	private String imgUrl;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Staff(int institutionId) {
		super();
		this.institutionId = institutionId;
	}
	

	public Staff( String staffNum, int positionNameId, int titleId, String name, String sex, String email,
			String phone, String qq, String mobile, String address, String zipCode, String nationality,
			String birthPlace, Date birthDate, String ethnicity, String religiousBelief, String politicalAffiliation,
			String idCard, String socialSecurityNumber, int age, String education, int educationYear,
			String educationMajor, int institutionId, String bank, String bankNum, String registrant,
			Date registrationTime, String specialty, String hobby, String personalResume, String familyRelationships,
			String remark, String state, String imgUrl) {
		super();
		StaffNum = staffNum;
		this.positionNameId = positionNameId;
		this.titleId = titleId;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.qq = qq;
		this.mobile = mobile;
		this.address = address;
		this.zipCode = zipCode;
		this.nationality = nationality;
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.ethnicity = ethnicity;
		this.religiousBelief = religiousBelief;
		this.politicalAffiliation = politicalAffiliation;
		this.idCard = idCard;
		this.socialSecurityNumber = socialSecurityNumber;
		this.age = age;
		this.education = education;
		this.educationYear = educationYear;
		this.educationMajor = educationMajor;
		this.institutionId = institutionId;
		this.bank = bank;
		this.bankNum = bankNum;
		this.registrant = registrant;
		this.registrationTime = registrationTime;
		this.specialty = specialty;
		this.hobby = hobby;
		this.personalResume = personalResume;
		this.familyRelationships = familyRelationships;
		this.remark = remark;
		this.state = state;
		this.imgUrl = imgUrl;
	}

	
	
	public Staff(int id, int titleId, String name, String sex, String email, String phone, String qq, String mobile,
			String address, String zipCode, String nationality, String birthPlace, Date birthDate, String ethnicity,
			String religiousBelief, String politicalAffiliation, String idCard, String socialSecurityNumber, int age,
			String education, int educationYear, String educationMajor, String bank, String bankNum, String registrant,
			Date registrationTime, String specialty, String hobby, String personalResume, String familyRelationships,
			String remark, String state, String imgUrl) {
		super();
		this.id = id;
		this.titleId = titleId;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.qq = qq;
		this.mobile = mobile;
		this.address = address;
		this.zipCode = zipCode;
		this.nationality = nationality;
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.ethnicity = ethnicity;
		this.religiousBelief = religiousBelief;
		this.politicalAffiliation = politicalAffiliation;
		this.idCard = idCard;
		this.socialSecurityNumber = socialSecurityNumber;
		this.age = age;
		this.education = education;
		this.educationYear = educationYear;
		this.educationMajor = educationMajor;
		this.bank = bank;
		this.bankNum = bankNum;
		this.registrant = registrant;
		this.registrationTime = registrationTime;
		this.specialty = specialty;
		this.hobby = hobby;
		this.personalResume = personalResume;
		this.familyRelationships = familyRelationships;
		this.remark = remark;
		this.state = state;
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStaffNum() {
		return StaffNum;
	}
	public void setStaffNum(String staffNum) {
		StaffNum = staffNum;
	}
	public int getPositionNameId() {
		return positionNameId;
	}
	public void setPositionNameId(int positionNameId) {
		this.positionNameId = positionNameId;
	}
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getReligiousBelief() {
		return religiousBelief;
	}
	public void setReligiousBelief(String religiousBelief) {
		this.religiousBelief = religiousBelief;
	}
	public String getPoliticalAffiliation() {
		return politicalAffiliation;
	}
	public void setPoliticalAffiliation(String politicalAffiliation) {
		this.politicalAffiliation = politicalAffiliation;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public int getEducationYear() {
		return educationYear;
	}
	public void setEducationYear(int educationYear) {
		this.educationYear = educationYear;
	}
	public String getEducationMajor() {
		return educationMajor;
	}
	public void setEducationMajor(String educationMajor) {
		this.educationMajor = educationMajor;
	}
	public int getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getRegistrant() {
		return registrant;
	}
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPersonalResume() {
		return personalResume;
	}
	public void setPersonalResume(String personalResume) {
		this.personalResume = personalResume;
	}
	public String getFamilyRelationships() {
		return familyRelationships;
	}
	public void setFamilyRelationships(String familyRelationships) {
		this.familyRelationships = familyRelationships;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
