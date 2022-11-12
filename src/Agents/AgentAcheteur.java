package Agents;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentAcheteur extends jade.core.Agent {
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
                        case ACLMessage.REQUEST:
                            System.out.println("message REQUEST reçu *****"+msg.getContent()+" ******");
                            ACLMessage msg2=msg.createReply();
                            msg2.setPerformative(ACLMessage.REQUEST);
                            msg2.setContent("demande reçu");
                            send(msg2);
                            System.out.println("message REQUEST envoyé *****"+msg2.getContent()+" ******");
                            ACLMessage msg3= new ACLMessage(ACLMessage.CFP);
                            msg3.addReceiver(new AID("vendeur 1",AID.ISLOCALNAME));
                            msg3.setContent(msg.getContent());
                            send(msg3);
                            System.out.println("message CFP envoyé *****"+msg3.getContent()+" ******");
                            break;
                        case ACLMessage.PROPOSE:
                            System.out.println("message PROPOSE reçu *****"+msg.getContent()+" ******");
                            ACLMessage msg4=msg.createReply();
                            msg4.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                            msg4.setContent("ok");
                            send(msg4);
                            System.out.println("message ACCEPT_PROPOSAL envoyé *****"+msg4.getContent()+" ******");
                            break;
                        case ACLMessage.CONFIRM:
                            System.out.println("message CONFIRM reçu *****"+msg.getContent()+" ******");
                            ACLMessage msg5= new ACLMessage(ACLMessage.INFORM);
                            msg5.addReceiver(new AID("Client 1",AID.ISLOCALNAME));
                            msg5.setContent("vente confirmée");
                            send(msg5);
                            System.out.println("message CONFIRM envoyé *****"+msg5.getContent()+" ******");
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
