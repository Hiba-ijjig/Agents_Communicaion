package Agents;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentClient extends jade.core.Agent {
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


        ParallelBehaviour paralleBehaviour=new ParallelBehaviour();
        addBehaviour(paralleBehaviour);
        paralleBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                try {
                    Thread.sleep(60*1000);
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
                ACLMessage msg= new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("acheteur 1",AID.ISLOCALNAME));
                msg.setContent("produit1");
                send(msg);
                System.out.println("message *****"+msg.getContent()+" ****** envoyé");
            }
        });
        paralleBehaviour.addSubBehaviour(new TickerBehaviour(this,1000) {
            @Override
            protected void onTick() {
                ACLMessage msg=receive();
                if(msg!=null) {
                    switch (msg.getPerformative()){
                        case ACLMessage.REQUEST: System.out.println("message REQUEST reçu *****"+msg.getContent()+" ******"); break;
                        case ACLMessage.INFORM: System.out.println("message INFORM reçu *****"+msg.getContent()+" ******"); break;
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
