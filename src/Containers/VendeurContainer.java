package Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class VendeurContainer {
    public static void main(String[] args) throws Exception {
        Runtime runtime =Runtime.instance();
        ProfileImpl profilImpl =new ProfileImpl();
        profilImpl.setParameter(profilImpl.MAIN_HOST, "localhost");
        AgentContainer container=runtime.createAgentContainer(profilImpl);
        AgentController agentController=container.createNewAgent("vendeur 1","Agents.AgentVendeur", new Object[] {"Bonjour"});
        agentController.start();
    }
}
