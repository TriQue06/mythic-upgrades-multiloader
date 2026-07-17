package net.trique.mythicupgrades.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;

/** Small adapter so tag providers can keep adding registry objects directly. */
final class ModTagAppender<T> {

    private final TagAppender<T> delegate;
    private final Registry<T> registry;

    ModTagAppender(TagAppender<T> delegate, Registry<T> registry) {
        this.delegate = delegate;
        this.registry = registry;
    }

    @SafeVarargs
    final ModTagAppender<T> add(T... values) {
        for (T value : values) {
            ResourceKey<T> key = registry.getResourceKey(value).orElseThrow(
                    () -> new IllegalStateException("Unregistered entry: " + value));
            delegate.add(key);
        }
        return this;
    }
}
