# Day 2 — Core Design Patterns (High Frequency)

## Goal
Recognize and apply design patterns **naturally**, driven by requirements — not forcefully.

Interview focus:
> Can the candidate control complexity as requirements evolve?

Using **1–2 patterns correctly** is far more valuable than applying many incorrectly.

---

## Patterns Introduced (Day 2)

- Strategy (pricing, payment) ✅
- Factory (optional, discussed conceptually)
- Observer (discussed, not implemented)
- Decorator (discussed, not implemented)
- Singleton (not used yet)

---

## Strategy Pattern — Pricing

### Requirement Trigger
> Pricing rules vary:
- Weekday
- Weekend
- Festival surge  
  We should add new rules **without modifying existing code**.

This is a clear **Open/Closed Principle (OCP)** signal.

---

### Why Strategy Fits
- Pricing behavior changes frequently
- Eliminates `if-else` chains
- Enables extension without modification

---

### Key Design Decision
> **PricingStrategy should not depend on domain entities.**

Pricing strategies operate on **data**, not domain objects.

❌ Avoid:
- Passing `Ticket`
- Passing `VehicleType` domain object

✅ Prefer:
- Duration / timestamps
- Vehicle category (value type)

---

### Pricing Strategy Abstraction

Ticket

paymentStatus (UNPAID / PAID)


Reason:
- Payment is part of the ticket lifecycle
- Avoids mixing workflow state into ParkingLot

---

### Payment Strategy Abstraction

Important rule:
> Payment strategies should not depend on domain entities.

❌ Avoid:
- `makePayment(Ticket)`

✅ Prefer:


PaymentStrategy

makePayment(amount, referenceId)


This mirrors real payment gateways and keeps strategies reusable.

---

## Payment Flow (Final)



Vehicle exits
→ ParkingLot
→ Retrieve Ticket
→ Ticket.calculateFee()
→ ParkingLot selects PaymentStrategy
→ PaymentStrategy.makePayment(amount, referenceId)
→ Ticket marked PAID
→ ParkingSlot freed


---

## Why We Removed Certain Things

### ❌ Removed `Payment` Wrapper Class
Reason:
- Added no responsibility
- Only delegated calls
- Increased confusion

Rule:
> If a class adds no abstraction or behavior, remove it.

---

## Factory Pattern (Optional, Discussed)

Factory can be used to:
- Select `PricingStrategy`
- Select `PaymentStrategy`

But:
- Not mandatory for Day 2
- Introduced only if selection logic becomes complex

Interview-safe line:
> “I introduce Factory only when creation logic starts leaking.”

---

## Observer & Decorator (Mention Only)

### Observer (Future Use)
- Notifications
- Receipts
- Analytics hooks

Mentioning it is enough for Day 2.

### Decorator (Future Use)
- Discounts
- Taxes
- Insurance add-ons

Not suitable for choosing payment modes.

---

## Key Takeaways (Day 2)

- Strategy is used **only when behavior varies**
- Domain entities stay clean and passive
- ParkingLot orchestrates workflows
- Patterns are introduced by **requirements**, not habit

---

## Interview Summary Line (Memorize This)

> “I apply Strategy to isolate changing behavior,  
keep domain entities stable,  
and centralize orchestration to avoid tight coupling.”