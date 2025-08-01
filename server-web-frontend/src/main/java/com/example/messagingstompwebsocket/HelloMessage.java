package com.example.messagingstompwebsocket;

import lombok.Data;
import lombok.NonNull;

@Data
public class HelloMessage {

	@NonNull private String name;

}
