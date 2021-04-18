package org.gradle.sample.transform.javamodules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Data class to hold the information that should be added as module-info.class to an existing Jar
 * file.
 */
public class ModuleInfo implements Serializable {
  private String moduleName;
  private String moduleVersion;
  private List<String> exports = new ArrayList<>();
  private List<String> requires = new ArrayList<>();
  private List<String> requiresStatic = new ArrayList<>();
  private List<String> requiresTransitive = new ArrayList<>();
  private Map<String, String> provides = new LinkedHashMap<>();

  ModuleInfo(String moduleName, String moduleVersion) {
    this.moduleName = moduleName;
    this.moduleVersion = moduleVersion;
  }

  public void exports(String exports) {
    this.exports.add(exports);
  }

  public void requires(String requires) {
    this.requires.add(requires);
  }

  public void requiresStatic(String requiresStatic) {
    this.requires.add(requiresStatic);
  }

  public void requiresTransitive(String requiresTransitive) {
    this.requiresTransitive.add(requiresTransitive);
  }

  public void provides(String provides, String provider) {
    this.provides.put(provides, provider);
  }

  public String getModuleName() {
    return moduleName;
  }

  protected String getModuleVersion() {
    return moduleVersion;
  }

  protected List<String> getExports() {
    return exports;
  }

  protected List<String> getRequires() {
    return requires;
  }

  protected List<String> getRequiresStatic() {
    return requiresStatic;
  }

  protected List<String> getRequiresTransitive() {
    return requiresTransitive;
  }

  public Map<String, String> getProvides() {
    return provides;
  }
}
