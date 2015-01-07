package models;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class MatrixBoard extends Agent{
	private static final long serialVersionUID = -5826558783363913396L;

	public MatrixBoard(){
		super();
		System.out.println("Matrix bord aangemaakt");
	}
	
	public void sendMessage(int edge_id, boolean closed){
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
	    msg.setContent(edge_id + ", " + closed);
	    for (int i = 1; i<=2; i++)
	        msg.addReceiver( new AID( "store" + i, AID.ISLOCALNAME) );
	    //AID dest = null;
	    //msg.addReceiver(dest);
	    send(msg);
	    
	}
	
	public void closeRoad(int edge_id, boolean closed){
		sendMessage(edge_id, true);
	}
	
	public void clearRoad(int edge_id, boolean closed){
		sendMessage(edge_id, false);
	}
}
