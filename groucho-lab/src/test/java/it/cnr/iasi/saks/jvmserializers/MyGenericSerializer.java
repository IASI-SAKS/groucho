/* This is a generic Serializer for FastJSONDatabind and FastJSONArrayDatabind */
package it.cnr.iasi.saks.jvmserializers;

import java.io.*;
import serializers.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.parser.Feature;

public class MyGenericSerializer<T> extends Serializer<T> {
	private final String name;
	private final Class<T> type;
	private int serializerFeatures;

	public MyGenericSerializer(String name, Class<T> clazz) {
		this.name = name;
		type = clazz;
		serializerFeatures |= SerializerFeature.QuoteFieldNames.getMask();
		serializerFeatures |= SerializerFeature.SkipTransientField.getMask();
		serializerFeatures |= SerializerFeature.SortField.getMask();
		serializerFeatures |= SerializerFeature.DisableCircularReferenceDetect.getMask();
		serializerFeatures |= SerializerFeature.BeanToArray.getMask();
	}

	@Override
	public String getName() {
		return name;
	}

	public void serializeItems(T[] items, OutputStream out) throws IOException {
		if (this.name == "json/fastjson/databind") {
			for (int i = 0, len = items.length; i < len; ++i) {
				JSON.writeJSONString(out, items[i], SerializerFeature.WriteEnumUsingToString,
						SerializerFeature.DisableCircularReferenceDetect);
			}
		} else {
			for (int i = 0, len = items.length; i < len; ++i) {
				JSON.writeJSONString(out,
						items[i],
						serializerFeatures);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(byte[] array) throws Exception {
		if (this.name == "json/fastjson/databind") {
			return (T) JSON.parseObject(array, type, Feature.DisableCircularReferenceDetect);
		} else {
			return (T) JSON.parseObject(array, type, Feature.SupportArrayToBean, Feature.DisableCircularReferenceDetect);
		}
	}

	@Override
	public byte[] serialize(T data) throws IOException {
		if (this.name == "json/fastjson/databind") {
			return JSON.toJSONBytes(data, SerializerFeature.WriteEnumUsingToString, SerializerFeature.DisableCircularReferenceDetect);
		} else {
			return JSON.toJSONBytes(data, serializerFeatures);
		}
	}
}
