import org.eclipse.jgit.api.Git;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

/**
 * Created by kiran on 3/13/16.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        Test tt = new Test();

//        tt.listRepos();
//        System.out.println(tt.createRepository("test-kiran155"));
        tt.removeRepo("knadigatla","test-kiran155");

    }

    public String createRepository(String projectName) throws IOException {
        GitHub gh = GitHub.connect("knadigatla","ce419ec2bbd9c1197547bd64270f23467f3c75ca");
        String name = gh.getMyself().getLogin()+"/"+projectName;
        try {
            GHRepository r = gh.getRepository(name);
            return r.toString();
        } catch (IOException e) {
            // No such repo found. No prob, we create it.
        }
        String descr = projectName + " created by MIDEaaS";
//        GHRepository r = gh.createRepository(projectName, descr, null, true);
        GHRepository r = gh.createRepository(projectName).description(descr).create();

        return r.toString();
    }

    public void listRepos() throws IOException {
        Collection<GHRepository> lst = GitHub.connect("knadigatla","ce419ec2bbd9c1197547bd64270f23467f3c75ca").getUser("knadigatla").getRepositories().values();
        for (GHRepository r : lst) {
            System.out.println(r.getName());
        }
        System.out.println(lst.size());

    }

    public synchronized void removeRepo(String owner, String repoName) {

        try {
            GitHub gitHub = GitHub.connect("knadigatla","ce419ec2bbd9c1197547bd64270f23467f3c75ca");

            if (!gitHub.isCredentialValid()) {
                System.out.println("Invalid GitHub credentials");
            }
            GHRepository repo = null;
            if (owner.compareToIgnoreCase(gitHub.getMyself().getLogin()) != 0) {
                GHOrganization org = gitHub.getOrganization(owner);
                repo = org.getRepository(repoName);
            } else {
                repo = gitHub.getRepository(owner + "/" + repoName);
            }
            repo.delete();

        } catch (IOException ex) {
            System.out.println("Problem authenticating with gihub-api when trying to remove a repository");
        }
    }



}
