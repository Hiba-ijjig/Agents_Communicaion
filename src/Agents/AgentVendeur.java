package Agents;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;

public class AgentVendeur extends jade.core.Agent {
    private HashMap<String,Double> produits;
    public AgentVendeur(){
        this.produits= new HashMap<String, Double>();
        this.produits.put("produit1",1000.0);
        this.produits.put("produit2",2000.0);
        this.produits.put("produit3",3000.0);
        this.produits.put("produit4",4000.0);
    }
    protected void setup() {
        Object[] args=getArguments();
        if(args.length!=0) {
            String message =(String) args[0];
            System.out.println("Hello! my name is "+this.getAID().getName()+" *** j'ai reçu votre message***"+message);
        }
        else {
            System.out.println("envoyer votre message!!");
            doDelete();
        }

        addBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                ACLMessage msg=receive();
                if(msg!=null) {
                    switch (msg.getPerformative()){
                        case ACLMessage.CFP:
                            System.out.println("message CFP reçu *****"+msg.getContent()+" ******");
                            ACLMessage msg2=msg.createReply();
                            msg2.setPerformative(ACLMessage.PROPOSE);
                            msg2.setContent(String.valueOf(produits.get(msg.getContent())));
                            send(msg2);
                            System.out.println("message PROPOSE envoyé *****"+msg2.getContent()+" ******");
                            break;
                        case ACLMessage.ACCEPT_PROPOSAL:
                            System.out.println("message PROPOSE reçu *****"+msg.getContent()+" ******");
                            ACLMessage msg4=msg.createReply();
                            msg4.setPerformative(ACLMessage.CONFIRM);
                            msg4.setContent("ok");
                            send(msg4);
                            System.out.println("message CONFIRM envoyé *****"+msg4.getContent()+" ******");
                            break;
                    }
                }
                else block();
            }
        });


    }
    protected void beforeMove() {
        System.out.println("Me coilà avant migration");
    }
    protected void afterMove() {
        System.out.println("Me coilà aprés migration");
    }
    protected void takeDown() {
        System.out.println("je meurs!!");
    }
}
