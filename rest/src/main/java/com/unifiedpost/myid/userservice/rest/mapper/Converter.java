package com.unifiedpost.myid.userservice.rest.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This converter apply for two objects which members in correspond have the same name
 * and the same primitive or wrapper type, regardless Object member types
 * Require getter and setter for object members
 *
 * @author HanhLC
 * @version 1.0
 * @since 2021/02/07
 */
@Slf4j
public class Converter<T> {

  private final static List<Class> WRAPPERS = Arrays.asList(Number.class, String.class, Date.class, LocalDate.class, LocalDateTime.class);

  public static <T> void convert(Object source, T target) {
    if (source == null || target == null) {
      return;
    }
    BeanUtils.copyProperties(source, target);
    List<Field> targetFields = Arrays.asList(target.getClass().getDeclaredFields());
    targetFields = removePrimitiveType(targetFields);
    targetFields = removeWrapperType(targetFields);

    List<Field> fields = Arrays.asList(source.getClass().getDeclaredFields());
    fields = removePrimitiveType(fields);
    fields = removeWrapperType(fields);
    for (Field field : fields) {
      // get field in target object has the same name to field in source object
      Optional<Field> fieldTargetOptional = targetFields.stream().filter(f -> f.getName().equals(field.getName())).findAny();
      if (!fieldTargetOptional.isPresent()) {
        continue;
      }
      Field fieldTarget = fieldTargetOptional.get();
      // convert to object and copy to field
      try {
        field.setAccessible(true);
        Object fromFieldObject = field.get(source);
        if (fromFieldObject != null) {
          try {
            Object targetFieldObject = fieldTarget.getType().newInstance();
            convert(fromFieldObject, targetFieldObject);
            fieldTarget.setAccessible(true);
            fieldTarget.set(target, targetFieldObject);
          } catch (InstantiationException e) {
            log.error("Cannot convert object " + e);
          }
        }
      } catch (IllegalAccessException e) {
        log.error("Cannot convert object " + e);
      }
    }
  }

  public static List<Field> removePrimitiveType(List<Field> fields) {
    return fields.stream().filter(field -> !field.getType().isPrimitive()).collect(Collectors.toList());
  }

  public static List<Field> removeWrapperType(List<Field> fields) {
    List<Field> results = new ArrayList<>();
    for (Field field : fields) {
      boolean isWrapper = WRAPPERS.stream().anyMatch(wrapper -> wrapper.isAssignableFrom(field.getType()));
      if (!isWrapper) {
        results.add(field);
      }
    }
    return results;
  }
}
