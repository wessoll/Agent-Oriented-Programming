package models;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class MatrixBoard extends Agent{
	public MatrixBoard(){
		super();
		System.out.println("Matrix bord aangemaakt");
	}
	
	public void sendMessage(String message){
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
	    msg.setContent(message);
	    for (int i = 1; i<=2; i++)
	        msg.addReceiver( new AID( "store" + i, AID.ISLOCALNAME) );
	    //AID dest = null;
	    //msg.addReceiver(dest);
	    send(msg);
	    
	}
	
	public void closeRoad(){
		sendMessage("Road is closed");
	}
	
	public void blockRoad(){
		sendMessage("1 line is closed");
	}
	
	public void clearRoad(){
		sendMessage("road is clear");
	}
}
