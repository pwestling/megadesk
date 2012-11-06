package com.liveramp.megadesk.serialization;

import org.yaml.snakeyaml.Yaml;

public class YamlSerialization implements Serialization<Object> {

  private final Yaml yaml = new Yaml();

  @Override
  public byte[] serialize(Object value) {
    return yaml.dump(value).getBytes();
  }

  @Override
  public Object deserialize(byte[] serializedValue) {
    if (serializedValue == null) {
      return null;
    } else {
      return yaml.load(new String(serializedValue));
    }
  }
}
