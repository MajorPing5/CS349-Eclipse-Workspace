package controller;

import view.*;
import model.*;
import my_util.*;
import java.awt.event.*;

public class CtrlrAuth {
	
	private AuthView aView;
	private AuthModel aModel;
	
	public CtrlrAuth(AuthView aView, AuthModel aModel) {
		aView = this.aView;
		aModel = this.aModel;
		
		// TODO Create button listener for btnLogin
		// NOTE Must use validateEmail before executing validatePassword
		// NOTE Use 'dispose();' when transitioning from frame-to-frame
	}
}
