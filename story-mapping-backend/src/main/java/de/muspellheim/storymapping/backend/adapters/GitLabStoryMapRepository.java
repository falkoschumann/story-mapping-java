package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.backend.StoryMapRepository;
import de.muspellheim.storymapping.contract.data.Project;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.SneakyThrows;

public class GitLabStoryMapRepository implements StoryMapRepository {
  // TODO https://docs.gitlab.com/ee/api/api_resources.html

  private final String baseUrl;
  private final String privateToken;
  private final String projectId;

  public GitLabStoryMapRepository(String baseUrl, String privateToken, String projectId) {
    this.baseUrl = baseUrl;
    this.privateToken = privateToken;
    this.projectId = projectId;
  }

  @Override
  public Project loadProject() {
    return null;
  }

  @Override
  @SneakyThrows
  public void storeProject(Project project) {
    var client = HttpClient.newHttpClient();

    var request =
        HttpRequest.newBuilder(URI.create(baseUrl + "/projects/" + projectId + "/labels"))
            .header("PRIVATE-TOKEN", privateToken)
            .POST(BodyPublishers.ofString("name=Todo&color=#c0c0c0"));
    var response = client.send(request.build(), BodyHandlers.ofString());
  }
}
