module de.muspellheim.storymapping.backend {
  requires de.muspellheim.storymapping.contract;
  requires java.net.http;
  requires jakarta.json;
  requires static lombok;

  exports de.muspellheim.storymapping.backend;
}
