package net.outmoded.outmodedEngine;

import com.github.retrooper.packetevents.protocol.entity.data.EntityData;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.entity.type.EntityType;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.world.states.WrappedBlockState;
import com.github.retrooper.packetevents.util.Quaternion4f;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.util.Vector3f;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import net.outmoded.outmodedEngine.live.Model;
import net.outmoded.outmodedEngine.live.Node;
import org.jetbrains.annotations.NotNull;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

public class PacketBuilder {

//    public void e() throws IllegalAccessException, LambdaConversionException {
//
//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//
//        // The accessor method we want to access
//        Method method = source.getClass().getDeclaredMethod("getLong");
//
//        MethodHandle methodHandle = lookup.unreflect(method);
//
//        // The type we want to bind our accessor to
//        MethodType factoryType = MethodType.methodType(Function.class);
//        // The expected return type of the accessor and the class the accessor is contained within
//        MethodType interfaceMethodType = MethodType.methodType(Long.class, source.getClass());
//
//        // A reference to the accessor via the Function intermediary
//        CallSite callSite = LambdaMetafactory.metafactory(lookup, "apply", factoryType, interfaceMethodType, methodHandle, methodHandle.type());
//
//        // The resultant Function that's now bound to the accessor
//        Function<SomeSource, Long> lambdaMetafactoryFunction = (Function<SomeSource, Long>) callSite.getTarget().invoke();
//    }


    public static class Entity {
        private int entityId;
        private final EntityType entityType;
        private UUID uuid;
        private Vector3d vector3d;
        private float pitch = 0;
        private float yaw = 0;
        private float headYaw = 0;


        public Entity(@NotNull("EntityType cannot be null") EntityType entityType){
            this.entityType = entityType;
        }

        public Entity entityId(int entityId){

            this.entityId = entityId;
            return this;
        }

        /**
         * set a specific uuid cannot be null
         */
        public Entity enforceUuid(@NotNull UUID uuid){
            this.uuid = uuid;
            return this;
        }

        public Entity vector(Vector3d vector3d){
            this.vector3d = vector3d;
            return this;
        }

        public Entity pitch(float pitch){
            this.pitch = pitch;
            return this;
        }

        public Entity yaw(float yaw){
            this.yaw = yaw;
            return this;
        }

        public Entity headYaw(float headYaw){
            this.headYaw = headYaw;
            return this;
        }

        public WrapperPlayServerSpawnEntity build() {

            UUID tempUuid = Objects.requireNonNullElseGet(uuid, UUID::randomUUID);

            return new WrapperPlayServerSpawnEntity(
                    entityId,
                    Optional.of(tempUuid),
                    entityType,
                    vector3d,
                    pitch,
                    yaw,
                    headYaw,
                    0,
                    Optional.empty()
            );
        }
    }

    public static class Update {
        List<EntityData<?>> data = new ArrayList<>();

        private final Node node; // stores all the data for visible players etc

        public Update(@NotNull Node node){
            this.node = node;
        }

        //8
        public Update interpolationDelay(int i){
            if (i > -1)
                data.add(new EntityData<>(8, EntityDataTypes.INT, i));
            return this;
        }

        //9
        public Update interpolation(int i){
            if (i > -1)
                data.add(new EntityData<>(9, EntityDataTypes.INT, i));
            return this;
        }

        //11
        public Update translation(Vector3f vector3f){
            data.add(new EntityData<>(11, EntityDataTypes.VECTOR3F, vector3f));
            return this;
        }

        //12
        public Update scale(Vector3f vector3f){
            data.add(new EntityData<>(12, EntityDataTypes.VECTOR3F, vector3f));
            return this;
        }

        //13
        public Update leftRotation(Quaternion4f quaternion4f){
            data.add(new EntityData(13, EntityDataTypes.QUATERNION, quaternion4f));
            return this;
        }

        //17
        public Update viewRange(float v){
            data.add(new EntityData<>(17, EntityDataTypes.FLOAT, v));
            return this;
        }

        // 23
        public Update itemstack(ItemStack itemStack){
            data.add(new EntityData(23, EntityDataTypes.ITEMSTACK, itemStack));
            return this;
        }

        // 23
        public Update block(WrappedBlockState blockState){
            data.add(new EntityData(23, EntityDataTypes.BLOCK_STATE, blockState.getGlobalId()));
            return this;
        }


        public WrapperPlayServerEntityMetadata build(){
            return new WrapperPlayServerEntityMetadata(node.getEntityId(),
                    data);
        }


    }

}
