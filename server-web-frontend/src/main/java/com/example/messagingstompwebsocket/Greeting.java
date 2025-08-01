package com.example.messagingstompwebsocket;

import lombok.Data;
import lombok.NonNull;

@Data
public class Greeting {

	@NonNull private String content;

}
