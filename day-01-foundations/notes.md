# Day 1 — LLD Foundations (Interview Mindset)

## What LLD Interviews Actually Evaluate

LLD interviews are NOT about:
- Design pattern memorization
- Framework knowledge
- Perfect UML diagrams

LLD interviews ARE about:
- Can you identify clear responsibilities?
- Can you name classes and methods meaningfully?
- Can your design adapt to changing requirements?
- Can another engineer read and understand your code without explanation?

Interviewers are evaluating:
> “Will this code be easy to extend, debug, and maintain in a real team?”

---

## The Golden Flow: Requirements → Objects → Interactions

A strong LLD solution always follows this order:

1. Clarify requirements
2. Identify core entities (nouns)
3. Assign responsibilities (verbs)
4. Define interactions between objects
5. Only then think about extensibility and abstractions

Most candidates fail by jumping directly to step 5.

---

## Inheritance vs Composition (Core Day-1 Concept)

### Key Insight

> Composition decouples behavior from identity.

Think in terms of **change**:

- What changes often? → **Behavior**
- What is stable? → **Entity identity**

Examples:
- Pricing, allocation, billing → change often
- Vehicle, ParkingSlot → mostly stable

Therefore:
- Use **composition** for change-prone logic
- Use **inheritance** only when behavior is truly invariant

Rule of thumb for interviews:
> “I use inheritance sparingly and prefer composition for flexibility.”

---

## Parking Lot — Domain Exploration

### Initial Core Entities (First Thought)

1. Vehicle
    - number
    - vehicleType
    - inTime
    - outTime

2. VehicleType
    - name
    - basePrice
    - hourlyPrice

3. ParkingSlot
    - slotNumber
    - floor
    - supportedVehicleType

4. Billing
    - number
    - duration
    - vehicleType

This version helps understand the domain but needs refinement.

---

## Refined & Interview-Safe Design

### Vehicle ✅

Responsibilities:
- Represents a real-world vehicle
- Holds identity and state only

Fields:
- number
- entryTime
- exitTime
- vehicleType

Important:
- Vehicle does NOT perform parking actions
- It remains a passive entity

---

### VehicleType ⚠️

Observations:
- Pricing values (`basePrice`, `hourlyPrice`) are volatile
- Pricing rules change based on:
    - Day (weekday vs weekend)
    - Time
    - Business rules

Conclusion:
- Pricing should eventually be delegated to a separate strategy
- For Day-1, keeping it simple is acceptable

---

### ParkingSlot ⚠️

Responsibilities:
- Knows whether it is available
- Knows which vehicle types it supports

Non-Responsibilities:
- Does NOT calculate price
- Does NOT track time
- Does NOT search other slots

Rule:
> An object should not manage or search its peers.

---

### Billing → Ticket (Renamed) ⚠️

Reason for rename:
- “Billing” implies payment processing
- “Ticket” represents parking session data

Responsibilities of Ticket:
- Holds entry and exit timestamps
- Knows assigned vehicle and slot
- Delegates fee calculation

Naming matters heavily in LLD interviews.

---

## Key Design Insight (Very Important)

> Entities that change frequently should not be tightly coupled to core domain objects.

- Vehicle → stable
- ParkingSlot → stable
- Ticket / Pricing → volatile

This is the fundamental reason **composition exists**.

---

## Final Core Entities (Day-1 Approved)

- ParkingLot (orchestrator)
- Vehicle
- VehicleType
- ParkingSlot
- Ticket

---

## Interaction Flow (High Level)

### Vehicle Entry Flow
1. Vehicle arrives at ParkingLot
2. ParkingLot checks for an available ParkingSlot
3. ParkingLot assigns a compatible slot
4. ParkingLot creates a Ticket
5. Ticket stores vehicle, slot, and entry time

### Vehicle Exit Flow
1. Exit request reaches ParkingLot
2. ParkingLot retrieves the Ticket
3. Ticket calculates fee (delegates pricing logic)
4. ParkingLot processes payment (out of scope)
5. ParkingLot marks ParkingSlot as available

---

## Design Principles Applied

- Single Responsibility Principle (SRP)
- Open/Closed Principle (OCP)
- Composition over inheritance
- Clear orchestration
- Passive entities, active coordinator

---

## Day-1 Takeaway

> Start with responsibilities,  
> isolate what changes,  
> use composition to protect core entities,  
> and keep the design minimal and readable.
