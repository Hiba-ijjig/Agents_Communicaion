package Containers;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;
import jade.core.ProfileImpl;
import jade.core.Runtime;

public class MainContainer {
    public static void main(String[] args) {
        Runtime runtime=Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(ProfileImpl.GUI, "true");
        AgentContainer mainContainer=runtime.createMainContainer(profileImpl);
        try {
            mainContainer.start();
        }catch(ControllerException e) {
            e.printStackTrace();
        }
    }
}
