package com.kinoroy.expo.push;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Helpful utility methods
 */
public class Util {

    /**
     * @param pushTicketResponse The response to extract the tokens from
     * @return A list of push tokens for messages that could not be delivered due to the token not being registered
     * (DeviceNotRegistered).
     */
    public static List<String> extractNotRegisteredTokens(PushTicketResponse pushTicketResponse) {
        if (pushTicketResponse.getTickets() == null) {
            return Collections.emptyList();
        }
        return pushTicketResponse.getTickets().stream()
                .map(Util::extractNotRegisteredToken)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * @param pushTicket The ticket to extract the token from
     * @return An optional invalid (DeviceNotRegistered) push token
     */
    public static Optional<String> extractNotRegisteredToken(PushTicket pushTicket) {
        String startStr = "ExponentPushToken";
        String endStr = "]";

        if (pushTicket.getDetails() != null && PushError.DEVICE_NOT_REGISTERED.equals(pushTicket.getDetails().getError())
                && pushTicket.getMessage() != null && pushTicket.getMessage().contains(startStr)) {
            return Optional.of(
                    pushTicket.getMessage().substring(pushTicket.getMessage().indexOf(startStr), pushTicket.getMessage().indexOf(endStr) + 1)
            );
        } else {
            return Optional.empty();
        }
    }
}
