package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notification_table")
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@ManyToOne
private User userToBeNotified;
private String approvalStatus;
private String blogTitle;
private String rejection;
public User getUserToBeNotified() {
	return userToBeNotified;
}
public void setUserToBeNotified(User userToBeNotified) {
	this.userToBeNotified = userToBeNotified;
}
private boolean views;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getApprovalStatus() {
	return approvalStatus;
}
public void setApprovalStatus(String approvalStatus) {
	this.approvalStatus = approvalStatus;
}
public String getBlogTitle() {
	return blogTitle;
}
public void setBlogTitle(String blogTitle) {
	this.blogTitle = blogTitle;
}
public String getRejection() {
	return rejection;
}
public void setRejection(String rejection) {
	this.rejection = rejection;
}
public boolean isViews() {
	return views;
}
public void setViews(boolean views) {
	this.views = views;
}

}
