package de.simone.backend.llm;

import java.util.function.*;

import dev.langchain4j.memory.chat.*;

public class TChatMemoryProvider implements Supplier<ChatMemoryProvider> {

    @Override
    public ChatMemoryProvider get() {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
    
}
