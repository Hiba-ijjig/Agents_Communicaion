package Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class ClientContainer {
    public static void main(String[] args) throws Exception {
        Runtime runtime =Runtime.instance();
        ProfileImpl profilImpl =new ProfileImpl();
        profilImpl.setParameter(profilImpl.MAIN_HOST, "localhost");
        AgentContainer container=runtime.createAgentContainer(profilImpl);
        AgentController agentController=container.createNewAgent("Client 1","Agents.AgentClient", new Object[] {"Bonjour"});
        agentController.start();
    }
}
