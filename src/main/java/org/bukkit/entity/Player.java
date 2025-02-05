package org.bukkit.entity;

import java.net.InetSocketAddress;
import java.util.UUID;

import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.WeatherType;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversable;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a player, connected or not
 */
public interface Player extends HumanEntity, Conversable, OfflinePlayer, PluginMessageRecipient, net.kyori.adventure.identity.Identified { // Paper

    // Paper start
    @Override
    default @NotNull net.kyori.adventure.identity.Identity identity() {
        return net.kyori.adventure.identity.Identity.identity(this.getUniqueId());
    }

    /**
     * Gets the "friendly" name to display of this player.
     *
     * @return the display name
     */
    @NotNull net.kyori.adventure.text.Component displayName();

    /**
     * Sets the "friendly" name to display of this player.
     *
     * @param displayName the display name to set
     */
    void displayName(final @Nullable net.kyori.adventure.text.Component displayName);
    // Paper end

    /**
     * Gets the "friendly" name to display of this player. This may include
     * color.
     * <p>
     * Note that this name will not be displayed in game, only in chat and
     * places defined by plugins.
     *
     * @return the friendly name
     * @deprecated in favour of {@link #displayName()}
     */
    @Deprecated // Paper
    @NotNull String getDisplayName();

    /**
     * Sets the "friendly" name to display of this player. This may include
     * color.
     * <p>
     * Note that this name will not be displayed in game, only in chat and
     * places defined by plugins.
     *
     * @param name The new display name.
     * @deprecated in favour of {@link #displayName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setDisplayName(@Nullable String name);

    // Paper start

    /**
     * Sets the name that is shown on the in-game player list.
     * <p>
     * If the value is null, the name will be identical to {@link #getName()}.
     *
     * @param name new player list name
     */
    void playerListName(@Nullable net.kyori.adventure.text.Component name);

    /**
     * Gets the name that is shown on the in-game player list.
     *
     * @return the player list name
     */
    @Nullable net.kyori.adventure.text.Component playerListName();

    /**
     * Gets the currently displayed player list header for this player.
     *
     * @return player list header or null
     */
    @Nullable net.kyori.adventure.text.Component playerListHeader();

    /**
     * Gets the currently displayed player list footer for this player.
     *
     * @return player list footer or null
     */
    @Nullable net.kyori.adventure.text.Component playerListFooter();
    // Paper end

    /**
     * Gets the name that is shown on the player list.
     *
     * @return the player list name
     * @deprecated in favour of {@link #playerListName()}
     */
    @NotNull
    @Deprecated // Paper
    String getPlayerListName();

    /**
     * Sets the name that is shown on the in-game player list.
     * <p>
     * If the value is null, the name will be identical to {@link #getName()}.
     *
     * @param name new player list name
     * @deprecated in favour of {@link #playerListName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setPlayerListName(@Nullable String name);

    /**
     * Gets the currently displayed player list header for this player.
     *
     * @return player list header or null
     * @deprecated in favour of {@link #playerListHeader()}
     */
    @Deprecated // Paper
    @Nullable String getPlayerListHeader();

    /**
     * Gets the currently displayed player list footer for this player.
     *
     * @return player list header or null
     * @deprecated in favour of {@link #playerListFooter()}
     */
    @Deprecated // Paper
    @Nullable String getPlayerListFooter();

    /**
     * Sets the currently displayed player list header for this player.
     *
     * @param header player list header, null for empty
     * @deprecated in favour of {@link #sendPlayerListHeader(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setPlayerListHeader(@Nullable String header);

    /**
     * Sets the currently displayed player list footer for this player.
     *
     * @param footer player list footer, null for empty
     * @deprecated in favour of {@link #sendPlayerListFooter(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setPlayerListFooter(@Nullable String footer);

    /**
     * Sets the currently displayed player list header and footer for this
     * player.
     *
     * @param header player list header, null for empty
     * @param footer player list footer, null for empty
     * @deprecated in favour of {@link #sendPlayerListHeaderAndFooter(net.kyori.adventure.text.Component, net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setPlayerListHeaderFooter(@Nullable String header, @Nullable String footer);

    /**
     * Set the target of the player's compass.
     *
     * @param loc Location to point to
     */
    void setCompassTarget(@NotNull Location loc);

    /**
     * Get the previously set compass target.
     *
     * @return location of the target
     */
    @NotNull Location getCompassTarget();

    /**
     * Gets the socket address of this player
     *
     * @return the player's address
     */
    @Nullable InetSocketAddress getAddress();

    /**
     * Sends this sender a message raw
     *
     * @param message Message to be displayed
     */
    @Override
    void sendRawMessage(@NotNull String message);

    /**
     * Kicks player with custom kick message.
     *
     * @param message kick message
     * @deprecated in favour of {@link #kick(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void kickPlayer(@Nullable String message);

    // Paper start

    /**
     * Kicks player with custom kick message.
     *
     * @param message kick message
     */
    void kick(final @Nullable net.kyori.adventure.text.Component message);
    // Paper end

    /**
     * Says a message (or runs a command).
     *
     * @param msg message to print
     */
    void chat(@NotNull String msg);

    /**
     * Makes the player perform the given command
     *
     * @param command Command to perform
     * @return true if the command was successful, otherwise false
     */
    boolean performCommand(@NotNull String command);

    /**
     * Returns true if the entity is supported by a block.
     * <p>
     * This value is a state updated by the client after each movement.
     *
     * @return True if entity is on ground.
     * @deprecated This value is controlled only by the client and is therefore
     * unreliable and vulnerable to spoofing and/or desync depending on the
     * context/time which it is accessed
     */
    @Override
    @Deprecated
    boolean isOnGround();

    /**
     * Returns if the player is in sneak mode
     *
     * @return true if player is in sneak mode
     */
    boolean isSneaking();

    /**
     * Sets the sneak mode the player
     *
     * @param sneak true if player should appear sneaking
     */
    void setSneaking(boolean sneak);

    /**
     * Gets whether the player is sprinting or not.
     *
     * @return true if player is sprinting.
     */
    boolean isSprinting();

    /**
     * Sets whether the player is sprinting or not.
     *
     * @param sprinting true if the player should be sprinting
     */
    void setSprinting(boolean sprinting);

    /**
     * Saves the players current location, health, inventory, motion, and
     * other information into the username.dat file, in the world/player
     * folder
     */
    void saveData();

    /**
     * Loads the players current location, health, inventory, motion, and
     * other information from the username.dat file, in the world/player
     * folder.
     * <p>
     * Note: This will overwrite the players current inventory, health,
     * motion, etc, with the state from the saved dat file.
     */
    void loadData();

    /**
     * Sets whether the player is ignored as not sleeping. If everyone is
     * either sleeping or has this flag set, then time will advance to the
     * next day. If everyone has this flag set but no one is actually in bed,
     * then nothing will happen.
     *
     * @param isSleeping Whether to ignore.
     */
    void setSleepingIgnored(boolean isSleeping);

    /**
     * Returns whether the player is sleeping ignored.
     *
     * @return Whether player is ignoring sleep.
     */
    boolean isSleepingIgnored();

    /**
     * Gets the Location where the player will spawn at their bed, null if
     * they have not slept in one or their current bed spawn is invalid.
     *
     * @return Bed Spawn Location if bed exists, otherwise null.
     */
    @Nullable
    @Override
    Location getBedSpawnLocation();

    /**
     * Sets the Location where the player will spawn at their bed.
     *
     * @param location where to set the respawn location
     */
    void setBedSpawnLocation(@Nullable Location location);

    /**
     * Sets the Location where the player will spawn at their bed.
     *
     * @param location where to set the respawn location
     * @param force    whether to forcefully set the respawn location even if a
     *                 valid bed is not present
     */
    void setBedSpawnLocation(@Nullable Location location, boolean force);

    /**
     * Play a note for a player at a location. This requires a note block
     * at the particular location (as far as the client is concerned). This
     * will not work without a note block. This will not work with cake.
     *
     * @param loc        The location of a note block.
     * @param instrument The instrument ID.
     * @param note       The note ID.
     * @deprecated Magic value
     */
    @Deprecated
    void playNote(@NotNull Location loc, byte instrument, byte note);

    /**
     * Play a note for a player at a location. This requires a note block
     * at the particular location (as far as the client is concerned). This
     * will not work without a note block. This will not work with cake.
     *
     * @param loc        The location of a note block
     * @param instrument The instrument
     * @param note       The note
     */
    void playNote(@NotNull Location loc, @NotNull Instrument instrument, @NotNull Note note);


    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location The location to play the sound
     * @param sound    The sound to play
     * @param volume   The volume of the sound
     * @param pitch    The pitch of the sound
     */
    void playSound(@NotNull Location location, @NotNull Sound sound, float volume, float pitch);

    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null. No
     * sound will be heard by the player if their client does not have the
     * respective sound for the value passed.
     *
     * @param location the location to play the sound
     * @param sound    the internal sound name to play
     * @param volume   the volume of the sound
     * @param pitch    the pitch of the sound
     */
    void playSound(@NotNull Location location, @NotNull String sound, float volume, float pitch);

    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location The location to play the sound
     * @param sound    The sound to play
     * @param category The category of the sound
     * @param volume   The volume of the sound
     * @param pitch    The pitch of the sound
     */
    void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch);

    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null. No sound
     * will be heard by the player if their client does not have the respective
     * sound for the value passed.
     *
     * @param location the location to play the sound
     * @param sound    the internal sound name to play
     * @param category The category of the sound
     * @param volume   the volume of the sound
     * @param pitch    the pitch of the sound
     */
    void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch);

    /**
     * Stop the specified sound from playing.
     *
     * @param sound the sound to stop
     */
    void stopSound(@NotNull Sound sound);

    /**
     * Stop the specified sound from playing.
     *
     * @param sound the sound to stop
     */
    void stopSound(@NotNull String sound);

    /**
     * Stop the specified sound from playing.
     *
     * @param sound    the sound to stop
     * @param category the category of the sound
     */
    void stopSound(@NotNull Sound sound, @Nullable SoundCategory category);

    /**
     * Stop the specified sound from playing.
     *
     * @param sound    the sound to stop
     * @param category the category of the sound
     */
    void stopSound(@NotNull String sound, @Nullable SoundCategory category);

    /**
     * Plays an effect to just this player.
     *
     * @param loc    the location to play the effect at
     * @param effect the {@link Effect}
     * @param data   a data bit needed for some effects
     * @deprecated Magic value
     */
    @Deprecated
    void playEffect(@NotNull Location loc, @NotNull Effect effect, int data);

    /**
     * Plays an effect to just this player.
     *
     * @param <T>    the data based based on the type of the effect
     * @param loc    the location to play the effect at
     * @param effect the {@link Effect}
     * @param data   a data bit needed for some effects
     */
    <T> void playEffect(@NotNull Location loc, @NotNull Effect effect, @Nullable T data);

    /**
     * Send a block change. This fakes a block change packet for a user at a
     * certain location. This will not actually change the world in any way.
     *
     * @param loc      The location of the changed block
     * @param material The new block
     * @param data     The block data
     * @deprecated Magic value
     */
    @Deprecated
    void sendBlockChange(@NotNull Location loc, @NotNull Material material, byte data);

    /**
     * Send a block change. This fakes a block change packet for a user at a
     * certain location. This will not actually change the world in any way.
     *
     * @param loc   The location of the changed block
     * @param block The new block
     */
    void sendBlockChange(@NotNull Location loc, @NotNull BlockData block);

    /**
     * Send block damage. This fakes block break progress for a user at a
     * certain location. This will not actually change the block's break
     * progress in any way.
     *
     * @param loc      the location of the damaged block
     * @param progress the progress from 0.0 - 1.0 where 0 is no damage and
     *                 1.0 is the most damaged
     */
    void sendBlockDamage(@NotNull Location loc, float progress);

    /**
     * Send a chunk change. This fakes a chunk change packet for a user at a
     * certain location. The updated cuboid must be entirely within a single
     * chunk. This will not actually change the world in any way.
     * <p>
     * At least one of the dimensions of the cuboid must be even. The size of
     * the data buffer must be 2.5*sx*sy*sz and formatted in accordance with
     * the Packet51 format.
     *
     * @param loc  The location of the cuboid
     * @param sx   The x size of the cuboid
     * @param sy   The y size of the cuboid
     * @param sz   The z size of the cuboid
     * @param data The data to be sent
     * @return true if the chunk change packet was sent
     * @deprecated Magic value
     */
    @Deprecated
    boolean sendChunkChange(@NotNull Location loc, int sx, int sy, int sz, @NotNull byte[] data);

    // Paper start

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc   the location of the sign
     * @param lines the new text on the sign or null to clear it
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     */
    void sendSignChange(@NotNull Location loc, @Nullable java.util.List<net.kyori.adventure.text.Component> lines) throws IllegalArgumentException;

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc      the location of the sign
     * @param lines    the new text on the sign or null to clear it
     * @param dyeColor the color of the sign
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if dyeColor is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     */
    void sendSignChange(@NotNull Location loc, @Nullable java.util.List<net.kyori.adventure.text.Component> lines, @NotNull DyeColor dyeColor) throws IllegalArgumentException;
    // Paper end

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc   the location of the sign
     * @param lines the new text on the sign or null to clear it
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     * @deprecated in favour of {@link #sendSignChange(org.bukkit.Location, java.util.List)}
     */
    @Deprecated // Paper
    void sendSignChange(@NotNull Location loc, @Nullable String[] lines) throws IllegalArgumentException;


    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc      the location of the sign
     * @param lines    the new text on the sign or null to clear it
     * @param dyeColor the color of the sign
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if dyeColor is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     * @deprecated in favour of {@link #sendSignChange(org.bukkit.Location, java.util.List, org.bukkit.DyeColor)}
     */
    @Deprecated // Paper
    void sendSignChange(@NotNull Location loc, @Nullable String[] lines, @NotNull DyeColor dyeColor) throws IllegalArgumentException;

    /**
     * Render a map and send it to the player in its entirety. This may be
     * used when streaming the map in the normal manner is not desirable.
     *
     * @param map The map to be sent
     */
    void sendMap(@NotNull MapView map);

    /**
     * Forces an update of the player's entire inventory.
     */
    //@Deprecated // Spigot - undeprecate
    void updateInventory();

    /**
     * Sets the current time on the player's client. When relative is true the
     * player's time will be kept synchronized to its world time with the
     * specified offset.
     * <p>
     * When using non relative time the player's time will stay fixed at the
     * specified time parameter. It's up to the caller to continue updating
     * the player's time. To restore player time to normal use
     * resetPlayerTime().
     *
     * @param time     The current player's perceived time or the player's time
     *                 offset from the server time.
     * @param relative When true the player time is kept relative to its world
     *                 time.
     */
    void setPlayerTime(long time, boolean relative);

    /**
     * Returns the player's current timestamp.
     *
     * @return The player's time
     */
    long getPlayerTime();

    /**
     * Returns the player's current time offset relative to server time, or
     * the current player's fixed time if the player's time is absolute.
     *
     * @return The player's time
     */
    long getPlayerTimeOffset();

    /**
     * Returns true if the player's time is relative to the server time,
     * otherwise the player's time is absolute and will not change its current
     * time unless done so with setPlayerTime().
     *
     * @return true if the player's time is relative to the server time.
     */
    boolean isPlayerTimeRelative();

    /**
     * Restores the normal condition where the player's time is synchronized
     * with the server time.
     * <p>
     * Equivalent to calling setPlayerTime(0, true).
     */
    void resetPlayerTime();

    /**
     * Sets the type of weather the player will see.  When used, the weather
     * status of the player is locked until {@link #resetPlayerWeather()} is
     * used.
     *
     * @param type The WeatherType enum type the player should experience
     */
    void setPlayerWeather(@NotNull WeatherType type);

    /**
     * Returns the type of weather the player is currently experiencing.
     *
     * @return The WeatherType that the player is currently experiencing or
     * null if player is seeing server weather.
     */
    @Nullable WeatherType getPlayerWeather();

    /**
     * Restores the normal condition where the player's weather is controlled
     * by server conditions.
     */
    void resetPlayerWeather();

    /**
     * Gives the player the amount of experience specified.
     *
     * @param amount Exp amount to give
     */
    void giveExp(int amount);

    /**
     * Gives the player the amount of experience levels specified. Levels can
     * be taken by specifying a negative amount.
     *
     * @param amount amount of experience levels to give or take
     */
    void giveExpLevels(int amount);

    /**
     * Gets the players current experience points towards the next level.
     * <p>
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @return Current experience points
     */
    float getExp();

    /**
     * Sets the players current experience points towards the next level
     * <p>
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @param exp New experience points
     */
    void setExp(float exp);

    /**
     * Gets the players current experience level
     *
     * @return Current experience level
     */
    int getLevel();

    /**
     * Sets the players current experience level
     *
     * @param level New experience level
     */
    void setLevel(int level);

    /**
     * Gets the players total experience points.
     * <br>
     * This refers to the total amount of experience the player has collected
     * over time and is not currently displayed to the client.
     *
     * @return Current total experience points
     */
    int getTotalExperience();

    /**
     * Sets the players current experience points.
     * <br>
     * This refers to the total amount of experience the player has collected
     * over time and is not currently displayed to the client.
     *
     * @param exp New total experience points
     */
    void setTotalExperience(int exp);

    /**
     * Send an experience change.
     * <p>
     * This fakes an experience change packet for a user. This will not actually
     * change the experience points in any way.
     *
     * @param progress Experience progress percentage (between 0.0 and 1.0)
     * @see #setExp(float)
     */
    void sendExperienceChange(float progress);

    /**
     * Send an experience change.
     * <p>
     * This fakes an experience change packet for a user. This will not actually
     * change the experience points in any way.
     *
     * @param progress New experience progress percentage (between 0.0 and 1.0)
     * @param level    New experience level
     * @see #setExp(float)
     * @see #setLevel(int)
     */
    void sendExperienceChange(float progress, int level);

    /**
     * Determines if the Player is allowed to fly via jump key double-tap like
     * in creative mode.
     *
     * @return True if the player is allowed to fly.
     */
    boolean getAllowFlight();

    /**
     * Sets if the Player is allowed to fly via jump key double-tap like in
     * creative mode.
     *
     * @param flight If flight should be allowed.
     */
    void setAllowFlight(boolean flight);

    /**
     * Hides a player from this player
     *
     * @param player Player to hide
     * @deprecated see {@link #hidePlayer(Plugin, Player)}
     */
    @Deprecated
    void hidePlayer(@NotNull Player player);

    /**
     * Hides a player from this player
     *
     * @param plugin Plugin that wants to hide the player
     * @param player Player to hide
     */
    void hidePlayer(@NotNull Plugin plugin, @NotNull Player player);

    /**
     * Allows this player to see a player that was previously hidden
     *
     * @param player Player to show
     * @deprecated see {@link #showPlayer(Plugin, Player)}
     */
    @Deprecated
    void showPlayer(@NotNull Player player);

    /**
     * Allows this player to see a player that was previously hidden. If
     * another another plugin had hidden the player too, then the player will
     * remain hidden until the other plugin calls this method too.
     *
     * @param plugin Plugin that wants to show the player
     * @param player Player to show
     */
    void showPlayer(@NotNull Plugin plugin, @NotNull Player player);

    /**
     * Checks to see if a player has been hidden from this player
     *
     * @param player Player to check
     * @return True if the provided player is not being hidden from this
     * player
     */
    boolean canSee(@NotNull Player player);

    /**
     * Checks to see if this player is currently flying or not.
     *
     * @return True if the player is flying, else false.
     */
    boolean isFlying();

    /**
     * Makes this player start or stop flying.
     *
     * @param value True to fly.
     */
    void setFlying(boolean value);

    /**
     * Sets the speed at which a client will fly. Negative values indicate
     * reverse directions.
     *
     * @param value The new speed, from -1 to 1.
     * @throws IllegalArgumentException If new speed is less than -1 or
     *                                  greater than 1
     */
    void setFlySpeed(float value) throws IllegalArgumentException;

    /**
     * Sets the speed at which a client will walk. Negative values indicate
     * reverse directions.
     *
     * @param value The new speed, from -1 to 1.
     * @throws IllegalArgumentException If new speed is less than -1 or
     *                                  greater than 1
     */
    void setWalkSpeed(float value) throws IllegalArgumentException;

    /**
     * Gets the current allowed speed that a client can fly.
     *
     * @return The current allowed speed, from -1 to 1
     */
    float getFlySpeed();

    /**
     * Gets the current allowed speed that a client can walk.
     *
     * @return The current allowed speed, from -1 to 1
     */
    float getWalkSpeed();

    /**
     * Request that the player's client download and switch texture packs.
     * <p>
     * The player's client will download the new texture pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * texture pack in the past, it will perform a file size check against
     * the response content to determine if the texture pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server textures on their client, in which
     *     case this method will have no affect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>There is no concept of resetting texture packs back to default
     *     within Minecraft, so players will have to relog to do so or you
     *     have to send an empty pack.
     * <li>The request is send with "null" as the hash. This might result
     *     in newer versions not loading the pack correctly.
     * </ul>
     *
     * @param url The URL from which the client will download the texture
     *            pack. The string must contain only US-ASCII characters and should
     *            be encoded as per RFC 1738.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long.
     * @deprecated Minecraft no longer uses textures packs. Instead you
     * should use {@link #setResourcePack(String)}.
     */
    @Deprecated
    void setTexturePack(@NotNull String url);

    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * resource pack in the past, it will perform a file size check against
     * the response content to determine if the resource pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no affect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>There is no concept of resetting resource packs back to default
     *     within Minecraft, so players will have to relog to do so or you
     *     have to send an empty pack.
     * <li>The request is send with "null" as the hash. This might result
     *     in newer versions not loading the pack correctly.
     * </ul>
     *
     * @param url The URL from which the client will download the resource
     *            pack. The string must contain only US-ASCII characters and should
     *            be encoded as per RFC 1738.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *                                  length restriction is an implementation specific arbitrary value.
     */
    void setResourcePack(@NotNull String url);

    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no affect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>There is no concept of resetting resource packs back to default
     *     within Minecraft, so players will have to relog to do so or you
     *     have to send an empty pack.
     * </ul>
     *
     * @param url  The URL from which the client will download the resource
     *             pack. The string must contain only US-ASCII characters and should
     *             be encoded as per RFC 1738.
     * @param hash The sha1 hash sum of the resource pack file which is used
     *             to apply a cached version of the pack directly without downloading
     *             if it is available. Hast to be 20 bytes long!
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *                                  length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException Thrown if the hash is null.
     * @throws IllegalArgumentException Thrown if the hash is not 20 bytes
     *                                  long.
     */
    void setResourcePack(@NotNull String url, @NotNull byte[] hash);

    /**
     * Gets the Scoreboard displayed to this player
     *
     * @return The current scoreboard seen by this player
     */
    @NotNull Scoreboard getScoreboard();

    /**
     * Sets the player's visible Scoreboard.
     *
     * @param scoreboard New Scoreboard for the player
     * @throws IllegalArgumentException if scoreboard is null
     * @throws IllegalArgumentException if scoreboard was not created by the
     *                                  {@link org.bukkit.scoreboard.ScoreboardManager scoreboard manager}
     * @throws IllegalStateException    if this is a player that is not logged
     *                                  yet or has logged out
     */
    void setScoreboard(@NotNull Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException;

    /**
     * Gets if the client is displayed a 'scaled' health, that is, health on a
     * scale from 0-{@link #getHealthScale()}.
     *
     * @return if client health display is scaled
     * @see Player#setHealthScaled(boolean)
     */
    boolean isHealthScaled();

    /**
     * Sets if the client is displayed a 'scaled' health, that is, health on a
     * scale from 0-{@link #getHealthScale()}.
     * <p>
     * Displayed health follows a simple formula <code>displayedHealth =
     * getHealth() / getMaxHealth() * getHealthScale()</code>.
     *
     * @param scale if the client health display is scaled
     */
    void setHealthScaled(boolean scale);

    /**
     * Sets the number to scale health to for the client; this will also
     * {@link #setHealthScaled(boolean) setHealthScaled(true)}.
     * <p>
     * Displayed health follows a simple formula <code>displayedHealth =
     * getHealth() / getMaxHealth() * getHealthScale()</code>.
     *
     * @param scale the number to scale health to
     * @throws IllegalArgumentException if scale is &lt;0
     * @throws IllegalArgumentException if scale is {@link Double#NaN}
     * @throws IllegalArgumentException if scale is too high
     */
    void setHealthScale(double scale) throws IllegalArgumentException;

    /**
     * Gets the number that health is scaled to for the client.
     *
     * @return the number that health would be scaled to for the client if
     * HealthScaling is set to true
     * @see Player#setHealthScale(double)
     * @see Player#setHealthScaled(boolean)
     */
    double getHealthScale();

    /**
     * Gets the entity which is followed by the camera when in
     * {@link GameMode#SPECTATOR}.
     *
     * @return the followed entity, or null if not in spectator mode or not
     * following a specific entity.
     */
    @Nullable Entity getSpectatorTarget();

    /**
     * Sets the entity which is followed by the camera when in
     * {@link GameMode#SPECTATOR}.
     *
     * @param entity the entity to follow or null to reset
     * @throws IllegalStateException if the player is not in
     *                               {@link GameMode#SPECTATOR}
     */
    void setSpectatorTarget(@Nullable Entity entity);

    /**
     * Sends a title and a subtitle message to the player. If either of these
     * values are null, they will not be sent and the display will remain
     * unchanged. If they are empty strings, the display will be updated as
     * such. If the strings contain a new line, only the first line will be
     * sent. The titles will be displayed with the client's default timings.
     *
     * @param title    Title text
     * @param subtitle Subtitle text
     * @deprecated API behavior subject to change
     */
    @Deprecated
    void sendTitle(@Nullable String title, @Nullable String subtitle);

    /**
     * Sends a title and a subtitle message to the player. If either of these
     * values are null, they will not be sent and the display will remain
     * unchanged. If they are empty strings, the display will be updated as
     * such. If the strings contain a new line, only the first line will be
     * sent. All timings values may take a value of -1 to indicate that they
     * will use the last value sent (or the defaults if no title has been
     * displayed).
     *
     * @param title    Title text
     * @param subtitle Subtitle text
     * @param fadeIn   time in ticks for titles to fade in. Defaults to 10.
     * @param stay     time in ticks for titles to stay. Defaults to 70.
     * @param fadeOut  time in ticks for titles to fade out. Defaults to 20.
     */
    void sendTitle(@Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Resets the title displayed to the player. This will clear the displayed
     * title / subtitle and reset timings to their default values.
     */
    void resetTitle();

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     */
    void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     */
    void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T>      type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, @Nullable T data);


    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T>      type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, @Nullable T data);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     */
    void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     */
    void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     */
    void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     */
    void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data);

    /**
     * Return the player's progression on the specified advancement.
     *
     * @param advancement advancement
     * @return object detailing the player's progress
     */
    @NotNull AdvancementProgress getAdvancementProgress(@NotNull Advancement advancement);

    /**
     * Get the player's current client side view distance.
     * <br>
     * Will default to the server view distance if the client has not yet
     * communicated this information,
     *
     * @return client view distance as above
     */
    int getClientViewDistance();

    // Paper start

    /**
     * Gets the player's current locale.
     *
     * @return the player's locale
     */
    @NotNull java.util.Locale locale();
    // Paper end

    /**
     * Gets the player's estimated ping in milliseconds.
     *
     * In Vanilla this value represents the average of the response time to the
     * last four application layer ping packets sent. This value does not
     * represent the network round trip time and as such may have less
     * granularity and be impacted by other sources. For these reasons it
     * <b>should not</b> be used for anti-cheat purposes. Its recommended use is
     * only as a <b>qualitative</b> indicator of connection quality (Vanilla
     * uses it for this purpose in the tab list).
     *
     * @return player ping
     */
    public int getPing();

    /**
     * Gets the player's current locale.
     * <p>
     * The value of the locale String is not defined properly.
     * <br>
     * The vanilla Minecraft client will use lowercase language / country pairs
     * separated by an underscore, but custom resource packs may use any format
     * they wish.
     *
     * @return the player's locale
     * @deprecated in favour of {@link #locale()}
     */
    @NotNull
    @Deprecated // Paper
    String getLocale();

    // Paper start

    /**
     * Get whether the player can affect mob spawning
     *
     * @return if the player can affect mob spawning
     */
    boolean getAffectsSpawning();

    /**
     * Set whether the player can affect mob spawning
     *
     * @param affects Whether the player can affect mob spawning
     */
    void setAffectsSpawning(boolean affects);
    // Paper end

    /**
     * Update the list of commands sent to the client.
     * <br>
     * Generally useful to ensure the client has a complete list of commands
     * after permission changes are done.
     */
    void updateCommands();

    /**
     * Open a {@link Material#WRITTEN_BOOK} for a Player
     *
     * @param book The book to open for this player
     */
    void openBook(@NotNull ItemStack book);

    // Paper start
    @NotNull
    @Override
    default net.kyori.adventure.text.event.HoverEvent<net.kyori.adventure.text.event.HoverEvent.ShowEntity> asHoverEvent(final @NotNull java.util.function.UnaryOperator<net.kyori.adventure.text.event.HoverEvent.ShowEntity> op) {
        return net.kyori.adventure.text.event.HoverEvent.showEntity(op.apply(net.kyori.adventure.text.event.HoverEvent.ShowEntity.of(this.getType().getKey(), this.getUniqueId(), this.displayName())));
    }
    // Paper end

    // Spigot start
    class Spigot extends Entity.Spigot {

        /**
         * Gets the connection address of this player, regardless of whether it
         * has been spoofed or not.
         *
         * @return the player's connection address
         */
        @NotNull
        public InetSocketAddress getRawAddress() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Gets whether the player collides with entities
         *
         * @return the player's collision toggle state
         * @deprecated see {@link LivingEntity#isCollidable()}
         */
        @Deprecated
        public boolean getCollidesWithEntities() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Sets whether the player collides with entities
         *
         * @param collides whether the player should collide with entities or
         *                 not.
         * @deprecated {@link LivingEntity#setCollidable(boolean)}
         */
        @Deprecated
        public void setCollidesWithEntities(boolean collides) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Respawns the player if dead.
         */
        public void respawn() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Gets all players hidden with {@link #hidePlayer(org.bukkit.entity.Player)}.
         *
         * @return a Set with all hidden players
         */
        @NotNull
        public java.util.Set<Player> getHiddenPlayers() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Deprecated // Paper
        @Override
        public void sendMessage(@NotNull net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Deprecated // Paper
        @Override
        public void sendMessage(@NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Sends the component to the specified screen position of this player
         *
         * @param position  the screen position
         * @param component the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@NotNull net.md_5.bungee.api.ChatMessageType position, @NotNull net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Sends an array of components as a single message to the specified screen position of this player
         *
         * @param position   the screen position
         * @param components the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@NotNull net.md_5.bungee.api.ChatMessageType position, @NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Sends the component to the specified screen position of this player
         *
         * @param position  the screen position
         * @param sender    the sender of the message
         * @param component the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@NotNull net.md_5.bungee.api.ChatMessageType position, @Nullable UUID sender, @NotNull net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Sends an array of components as a single message to the specified screen position of this player
         *
         * @param position   the screen position
         * @param sender     the sender of the message
         * @param components the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@NotNull net.md_5.bungee.api.ChatMessageType position, @Nullable UUID sender, @NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        // Paper start
        public int getPing() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        // Paper end
    }

    @NotNull
    @Override
    Spigot spigot();
    // Spigot end

    // Mohist start
    default boolean isFakePlayer() {
        return false;
    }
    // Mohist end
}
